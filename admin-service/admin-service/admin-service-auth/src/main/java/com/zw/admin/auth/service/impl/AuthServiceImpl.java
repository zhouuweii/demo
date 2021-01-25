package com.zw.admin.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.zw.admin.auth.client.UserClient;
import com.zw.admin.auth.service.AuthService;
import com.zw.admin.framework.common.constant.CacheConstants;
import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.common.utils.ServletUtils;
import com.zw.admin.framework.common.utils.ip.IpUtils;
import com.zw.admin.framework.core.service.RedisTemplateUtil;
import com.zw.admin.framework.domain.code.AuthCode;
import com.zw.admin.framework.domain.model.AuthToken;
import com.zw.admin.framework.domain.model.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户认证接口实现类
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Value("${server.port}")
    private String ServerPort;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Value("${auth.tokenValiditySeconds}")
    private long tokenValiditySeconds;

    /**
     * 申请令牌，并将令牌存储到redis中。
     * @param username 用户名
     * @param password 用户密码
     * @param clientId 客户端ID
     * @param clientSecret 客户端密码
     * @return com.zw.framework.core.domain.model.AuthToken
     **/
    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        //查询用户信息，判断冻结、删除等状态
        ResultData resultData = userClient.getInfo(username);
        LoginUser userInfo = JSON.parseObject(JSON.toJSONString(resultData.getData()), LoginUser.class);
        if (userInfo == null) {
            System.out.println("用户不存在，用户已冻结...");
            return null;
        }
        //状态等正常则请求spring security申请令牌
        AuthToken authToken = this.applyToken(username, password, clientId, clientSecret);
        if (authToken == null) {
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }
        //完善登陆的用户信息
        userInfo.setToken(authToken.getJti());
        userInfo.setUserid(userInfo.getSysUser().getUserId());
        userInfo.setUsername(userInfo.getSysUser().getUserName());
        userInfo.setExpireTime(authToken.getExpires_in().longValue());
        userInfo.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        //将令牌存储到Redis
        String key =  CacheConstants.LOGIN_TOKEN_KEY + authToken.getJti();
        redisTemplateUtil.setCacheObject(key, userInfo, tokenValiditySeconds, TimeUnit.SECONDS);
        return authToken;
    }

    @Override
    public void logout(String loginName) {

    }

    /**
     * 远程请求Spring Security申请令牌
     * @param username 用户名
     * @param password 用户密码
     * @param clientId 客户端ID
     * @param clientSecret 客户端密码
     * @return com.zw.framework.core.domain.model.AuthToken
     **/
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret) {
        String authUrl = "http://127.0.0.1:" + ServerPort + "/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        //进行Base64编码
        byte[] encode = Base64Utils.encode((clientId + ":" + clientSecret).getBytes());
        String httpBasic = "Basic " + new String(encode);
        header.add("Authorization", httpBasic);
        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        //申请令牌信息
        Map bodyMap = exchange.getBody();
        if (bodyMap == null || bodyMap.get("access_token") == null || bodyMap.get("refresh_token") == null || bodyMap.get("jti") == null) {
            //解析spring security返回的错误信息
            String error_description = (String) bodyMap.get("error_description");
            if (StringUtils.isNotEmpty(error_description)) {
                if (error_description.equals("坏的凭证")) {
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                } else if (error_description.indexOf("UserDetailsService returned null") >= 0) {
                    ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
                }
            }
        }
        AuthToken authToken = new AuthToken(bodyMap.get("access_token").toString(), bodyMap.get("token_type").toString(),
                bodyMap.get("refresh_token").toString(), Integer.parseInt(bodyMap.get("expires_in").toString()),
                bodyMap.get("scope").toString(), bodyMap.get("jti").toString());
        return authToken;
    }
}
