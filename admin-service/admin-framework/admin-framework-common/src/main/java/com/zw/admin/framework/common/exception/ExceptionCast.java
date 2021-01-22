package com.zw.admin.framework.common.exception;

import com.zw.admin.framework.common.response.ResponseMain;

/**
 * 自定义异常类型
 * @author: ZhouWei
 * @create: 2021-01
 **/
public class ExceptionCast {

    public static void cast(ResponseMain resultCode) {
        throw new CustomException(resultCode);
    }
}
