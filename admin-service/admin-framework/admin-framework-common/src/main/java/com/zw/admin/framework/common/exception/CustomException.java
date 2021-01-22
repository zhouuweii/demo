package com.zw.admin.framework.common.exception;

import com.zw.admin.framework.common.response.ResponseMain;

/**
 * 自定义异常类型
 * @author: ZhouWei
 * @create: 2021-01
 **/
public class CustomException extends RuntimeException {

    private ResponseMain responseMain;

    public CustomException(ResponseMain resultCode) {
        super("错误代码：" + resultCode.code() + "错误信息：" + resultCode.message());
        this.responseMain = resultCode;
    }

    public ResponseMain getResultCode() {
        return this.responseMain;
    }
}
