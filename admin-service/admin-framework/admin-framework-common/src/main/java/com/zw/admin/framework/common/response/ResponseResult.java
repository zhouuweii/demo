package com.zw.admin.framework.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 响应结果集主体
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements ResponseCode {

    /**操作是否成功*/
    boolean success = SUCCESS;

    /**操作代码*/
    int code = SUCCESS_CODE;

    /**提示信息*/
    String message;

    public ResponseResult(ResponseMain responseMain) {
        this.success = responseMain.success();
        this.code = responseMain.code();
        this.message = responseMain.message();
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static ResponseResult FAIL() {
        return new ResponseResult(CommonCode.FAIL);
    }

}
