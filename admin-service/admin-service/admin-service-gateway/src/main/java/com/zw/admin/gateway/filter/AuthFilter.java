package com.zw.admin.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResponseResult;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.gateway.config.IgnoreWhiteProperties;
import com.zw.admin.gateway.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;

/**
 * 身份校验过滤器
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    /**放行白名单配置*/
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
        ServerHttpRequest request = exchange.getRequest();
//        String tokenFromCookie = authService.getTokenFromCookie(request);
//        if (StringUtils.isEmpty(tokenFromCookie)) {
//            return this.access_denied(exchange, CommonCode.UNAUTHENTICATED_COOKIE);
//        }
        //从header标头取出jwt令牌
        String jwtFromHeader = authService.getJwtFromHeader(request);
        if (StringUtils.isEmpty(jwtFromHeader)) {
            return this.access_denied(exchange, CommonCode.UNAUTHENTICATED_TOKEN);
        }
        //从Redis取出jwt的有效期
//        long expire = authService.getExpire(tokenFromCookie);
//        if (expire < 0) {
//            return this.access_denied(exchange, CommonCode.UNAUTHENTICATED_EXPIRE);
//        }
        return chain.filter(exchange);
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
