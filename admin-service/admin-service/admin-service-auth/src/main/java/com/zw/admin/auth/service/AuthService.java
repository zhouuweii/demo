package com.zw.admin.auth.service;


import com.zw.admin.framework.domain.model.AuthToken;

/**
 * 用户认证接口
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface AuthService {

    /**
     * 登陆
     * @param username 用户名
     * @param password 用户密码
     * @param clientId 客户端ID
     * @param clientSecret 客户端密码
     * @return com.zw.framework.domain.system.user.extend.AuthToken
     **/
    public AuthToken login(String username, String password, String clientId, String clientSecret);

    /**
     * 退出登陆
     * @param loginName 用户名
     * @return void
     **/
    public void logout(String loginName);

}
