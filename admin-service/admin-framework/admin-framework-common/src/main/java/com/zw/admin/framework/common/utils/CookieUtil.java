package com.zw.admin.framework.common.utils;

import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cookie操作工具类
 * @author: ZhouWei
 * @create: 2020-12
 **/
public class CookieUtil {

    /**
     * 设置cookie
     * @param response  response
     * @param domain    域名
     * @param path      路径
     * @param name      cookie名字
     * @param value     cookie值
     * @param maxAge    cookie生命周期 以秒为单位
     * @param httpOnly  false则浏览器可以获取cookie
     * @return void
     **/
    public static void addCookie(HttpServletResponse response, String domain, String path, String name,
                                 String value, int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    /**
     * 根据cookie名称读取cookie
     * @param request   HttpServletRequest
     * @param cookieNames   cookie名称
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    public static Map<String, String> readCookie(HttpServletRequest request, String... cookieNames) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                for (int i = 0; i < cookieNames.length; i++) {
                    if (cookieNames[i].equals(cookieName)) {
                        cookieMap.put(cookieName, cookieValue);
                    }
                }
            }
        }
        return cookieMap;
    }

    /**
     * 根据cookie名称读取cookie
     * @param request   ServerHttpRequest
     * @param cookieNames   cookie名称
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    public static Map<String, String> readCookie(ServerHttpRequest request, String... cookieNames) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        for (Map.Entry<String, List<HttpCookie>> entry : cookies.entrySet()) {
            List<HttpCookie> value = entry.getValue();
            for (HttpCookie cookie : value) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                for (int i = 0; i < cookieNames.length; i++) {
                    if (cookieNames[i].equals(cookieName)) {
                        cookieMap.put(cookieName, cookieValue);
                    }
                }
            }
        }
        return cookieMap;
    }
}
