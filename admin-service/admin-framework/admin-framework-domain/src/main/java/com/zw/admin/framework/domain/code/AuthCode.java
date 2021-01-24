package com.zw.admin.framework.domain.code;

import com.zw.admin.framework.common.response.ResponseMain;
import lombok.ToString;

/**
 * @description: 认证服务错误代码
 * @author: ZhouWei
 * @create: 2021-01
 **/
@ToString
public enum AuthCode implements ResponseMain {

    //认证服务错误代码
    AUTH_USERNAME_NONE(false, 23001, "请输入账号！"),
    AUTH_PASSWORD_NONE(false, 23002, "请输入密码！"),
    AUTH_VERIFYCODE_NONE(false, 23003, "请输入验证码！"),
    AUTH_ACCOUNT_NOTEXISTS(false, 23004, "账号不存在！"),
    AUTH_CREDENTIAL_ERROR(false, 23005, "账号或密码错误！"),
    AUTH_LOGIN_ERROR(false, 23006, "登陆过程出现异常请尝试重新操作！"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(false, 23007, "申请令牌失败！"),
    AUTH_LOGIN_TOKEN_SAVEFAIL(false, 23008, "存储令牌失败！"),
    AUTH_LOGIN_AUTHSERVER_NOTFOUND(false, 23009, "没有找到！"),
    AUTH_LOGIN_FROZEN(false, 23010, "账户异常或已被冻结，暂时无法登陆！"),
    AUTH_LOGOUT_FAIL(false, 23011, "退出失败！");

    /**操作是否成功*/
    private boolean success;

    /**操作代码*/
    private int code;

    /**提示信息*/
    private String message;

    private AuthCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
