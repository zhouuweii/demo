package com.zw.admin.framework.common.exceptions.user;


import com.zw.admin.framework.common.exceptions.BaseException;

/**
 * 用户信息异常类
 * @author: ZhouWei
 * @create: 2021-01
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
