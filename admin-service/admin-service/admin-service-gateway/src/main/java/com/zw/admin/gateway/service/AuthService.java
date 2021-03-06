package com.zw.admin.gateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 身份校验
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface AuthService {

    /**
     * 从cookie查询身份令牌
     * @param request 请求头
     * @return java.lang.String
     **/
    public String getTokenFromCookie(ServerHttpRequest request);

    /**
     * 从header标头取出jwt令牌
     * @param request 请求头
     * @return java.lang.String
     **/
    public String getTokenByHeader(ServerHttpRequest request);

}
