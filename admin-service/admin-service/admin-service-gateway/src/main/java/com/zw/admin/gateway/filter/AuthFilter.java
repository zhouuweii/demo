package com.zw.admin.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zw.admin.framework.common.constant.CacheConstants;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResponseResult;
import com.zw.admin.framework.common.utils.ServletUtils;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.gateway.config.IgnoreWhiteProperties;
import com.zw.admin.gateway.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 身份校验过滤器
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> sops;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    /**放行白名单配置,NaCos自行添加*/
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    /**
     * 自定义过滤器
     * @param exchange
     * @param chain
     * @return reactor.core.publisher.Mono<java.lang.Void>
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 跳过不需要验证的路径
        String url = exchange.getRequest().getURI().getPath();
        if (StringUtils.matches(url, ignoreWhite.getWhites())) {
            return chain.filter(exchange);
        }
        //从cookie查询身份令牌
//        String tokenFromCookie = authService.getTokenFromCookie(exchange.getRequest());
//        if (StringUtils.isEmpty(tokenFromCookie)) {
//            return this.access_denied(exchange, CommonCode.UNAUTHENTICATED_COOKIE);
//        }
        //从Header标头取出令牌
        String token = authService.getTokenByHeader(exchange.getRequest());
        if (StringUtils.isEmpty(token)) {
            return this.access_denied(exchange, CommonCode.UNAUTHENTICATED_TOKEN);
        }
        //从Redis取出Token
        String userStr = sops.get(CacheConstants.LOGIN_TOKEN_KEY + token);
        if (StringUtils.isNull(userStr)) {
            return this.access_denied(exchange, CommonCode.UNAUTHENTICATED_EXPIRE);
        }
        //设置用户id、username
        JSONObject json = JSONObject.parseObject(userStr);
        String userid = json.getString("userid");
        String username = json.getString("username");
        if (StringUtils.isBlank(userid) || StringUtils.isBlank(username)) {
            return this.access_denied(exchange, CommonCode.UNAUTHENTICATED_ERROR);
        }
        // 设置用户信息到请求
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(CacheConstants.DETAILS_USER_ID, userid)
                .header(CacheConstants.DETAILS_USERNAME, ServletUtils.urlEncode(username)).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    /**
     * 拒绝访问
     * @return void
     **/
    private Mono<Void> access_denied(ServerWebExchange exchange, CommonCode commonCode) {
        ServerHttpResponse resp = exchange.getResponse();
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        ResponseResult responseResult = new ResponseResult(commonCode);
        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(responseResult);
        } catch (JsonProcessingException e) {
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    /**
     * 过滤器序号，越小越被先执行
     * @return int
     **/
    @Override
    public int getOrder() {
        return 0;
    }
}
