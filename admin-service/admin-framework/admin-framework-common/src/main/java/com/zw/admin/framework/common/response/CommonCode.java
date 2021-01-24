package com.zw.admin.framework.common.response;

import lombok.ToString;

import java.io.Serializable;

/**
 * 通用类型数据响应代码
 * @author: ZhouWei
 * @create: 2021-01
 **/
@ToString
public enum CommonCode implements ResponseMain, Serializable {

    //操作成功
    SUCCESS(true, 200, "操作成功！"),

    //基本错误
    UNAUTHENTICATED(false, 2001, "此操作需要登陆系统！"),
    UNAUTHENTICATED_COOKIE(false, 2002, "认证失败！缺少cookie！"),
    UNAUTHENTICATED_TOKEN(false, 2003, "认证失败！缺少token！"),
    UNAUTHENTICATED_EXPIRE(false, 2004, "认证过期！"),
    UNAUTHENTICATED_ERROR(false, 2005, "认证失败，无此用户！"),
    UNAUTHORISE(false, 2006, "权限不足，无权操作！"),

    //调用失败
    FAIL(false, 3001, "操作失败！"),
    PARAM_LACK(false, 1601, "缺少参数，请补齐参数！"),
    PARAM_LACK_OR(false, 1602, "缺少参数,请至少传入一个参数！"),
    PARAM_INVALID(false, 1603, "非法参数，请重新输入！"),
    PARAM_DATE_FORMAT_ERROR(false, 1604, "日期格式错误，请输入此[2020-01-01 00:00:00]格式的日期！"),
    PARAM_PHONE_ERROR(false, 1605, "手机号格式不正确"),
    DATA_ALREADY_EXISTS(false, 1701, "你插入的数据已存在"),
    DATA_ALREADY_EXISTS_BUCKET(false, 1702, "你插入的table名称已存在，请换个table名称"),
    DATA_ALREADY_DELETE(false, 1703, "该数据不存在或已被删除"),
    DATA_EXISTS_USER_NAME(false, 98011001, "该用户名已存在"),
    DATA_EXISTS_USER_PHONE(false, 98011002, "该手机号已被使用"),
    DATA_EXISTS_USER_EMAIL(false, 98011003, "该邮箱已被使用"),
    //调用失败：远程调用失败
    FEIGN_MESSAGE_ERROR(false, 3101, "此接口调用了消息中心服务，该服务异常！"),

    //调用成功：过程错误
    GET_PHONE_CODE_ERROR(false, 4001, "获取手机验证码失败"),
    GET_PHONE_CODE_OFTEN(false, 4002, "发送短信频繁，请稍后再试"),
    INSERT_ERROR(false, 4003, "新增数据失败"),
    UPDATE_ERROR(false, 4003, "更新数据失败"),

    //调用成功：但未查询到数据
    DATA_NOT_EXISTS(false, 4101, "您查询的数据不存在"),
    DATA_NOT_EXISTS_USER(false, 4102, "此用户不存在"),
    DATA_PHONE_CODE_ERROR(false, 4103, "验证码错误"),

    //通用错误
    SERVER_ERROR(false, 9999, "抱歉，系统繁忙，请稍后重试！");

    /**操作是否成功*/
    private boolean success;

    /**操作代码*/
    private int code;

    /**提示信息*/
    private String message;

    private CommonCode(boolean success, int code, String message) {
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
