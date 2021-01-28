package com.zw.admin.framework.domain.request;

import lombok.Data;
import lombok.ToString;

/**
 * 用户注册
 * @author: ZhouWei
 * @create: 2021-01
 */
@Data
@ToString
public class Register {

    /**用户名*/
    private String username;

    /**用户密码*/
    private String password;

    /**手机号码*/
    private String phone;

    /**手机短信验证码*/
    private String code;

    /**手机短信验证码 唯一标识*/
    private String smsId = "";

}
