package com.zw.admin.gateway.service.impl;

import com.zw.admin.framework.common.utils.CookieUtil;
import com.zw.admin.gateway.service.AuthService;
import org.apache.commons.lang3.StringUtils;
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
    public String getTokenByHeader(ServerHttpRequest request) {
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
}
