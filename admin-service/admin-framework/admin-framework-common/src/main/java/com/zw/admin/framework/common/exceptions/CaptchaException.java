package com.zw.admin.framework.common.exceptions;

/**
 * 验证码错误异常类
 * @author: ZhouWei
 * @create: 2021-01
 */
public class CaptchaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
