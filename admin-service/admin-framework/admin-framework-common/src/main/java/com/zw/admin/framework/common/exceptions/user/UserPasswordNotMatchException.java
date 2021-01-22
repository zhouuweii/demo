package com.zw.admin.framework.common.exceptions.user;

/**
 * 用户密码不正确或不符合规范异常类
 * @author: ZhouWei
 * @create: 2021-01
 */
public class UserPasswordNotMatchException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
