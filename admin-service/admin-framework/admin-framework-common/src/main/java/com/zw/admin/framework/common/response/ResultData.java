package com.zw.admin.framework.common.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通用数据响应结果集
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class ResultData<T> extends ResponseResult implements Serializable {

    private T data;

    public ResultData() {
    }

    public ResultData(ResponseMain responseMain, T data) {
        super(responseMain);
        this.data = data;
    }

}
