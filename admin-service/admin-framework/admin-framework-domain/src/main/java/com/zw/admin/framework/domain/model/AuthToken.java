package com.zw.admin.framework.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description: 认证令牌
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
@NoArgsConstructor
public class AuthToken {

    /**access_token*/
    private String access_token;

    /**token_type*/
    private String token_type;

    /**刷新token*/
    private String refresh_token;

    /**expires_in*/
    private Integer expires_in;

    /**scope*/
    private String scope;

    /**jti*/
    private String jti;

    public AuthToken(String access_token, String token_type, String refresh_token, Integer expires_in, String scope, String jti) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.scope = scope;
        this.jti = jti;
    }
}

