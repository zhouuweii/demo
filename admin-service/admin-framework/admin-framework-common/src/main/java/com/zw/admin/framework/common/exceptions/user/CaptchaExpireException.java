package com.zw.admin.framework.common.exceptions.user;

/**
 * 验证码失效异常类
 * @author: ZhouWei
 * @create: 2021-01
 */
public class CaptchaExpireException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super("user.jcaptcha.expire", null);
    }
}
