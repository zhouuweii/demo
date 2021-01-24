package com.zw.admin.auth.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 用户信息扩展JWT信息
 * 此模型类引用了Spring security，暂不存入admin-framework-domain
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class UserJwt extends User {

    private String id;
    private String name;
    private String userPicture;

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
