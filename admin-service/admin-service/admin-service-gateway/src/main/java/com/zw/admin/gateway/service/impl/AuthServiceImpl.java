package com.zw.admin.gateway.service.impl;

import com.zw.admin.framework.common.utils.CookieUtil;
import com.zw.admin.gateway.config.RedisTemplateUtil;
import com.zw.admin.gateway.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 身份校验
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    /**
     * 从cookie查询身份令牌
     * @param request 请求头
     * @return java.lang.String
     **/
    @Override
    public String getTokenFromCookie(ServerHttpRequest request) {
        Map<String, String> cookieMap = CookieUtil.readCookie(request, "uid");
        String access_token = cookieMap.get("uid");
        if (StringUtils.isEmpty(access_token)) {
            return null;
        }
        return access_token;
    }

    /**
     * 从header标头取出jwt令牌
     * @param request 请求头
     * @return java.lang.String
     **/
    @Override
    public String getJwtFromHeader(ServerHttpRequest request) {
        //header标头
        String authorization = request.getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            return null;
        }
        if (!authorization.startsWith("Bearer ")) {
            return null;
        }
        //取到jwt令牌
        String jwt = authorization.substring(7);
        return jwt;
    }

    /**
     * 从Redis取出jwt的有效期
     * @param access_token 身份令牌
     * @return long
     **/
    @Override
    public long getExpire(String access_token) {
        String key = "user_token:"+access_token;
        long expire = redisTemplateUtil.getExpire(key);
        return expire;
    }
}
