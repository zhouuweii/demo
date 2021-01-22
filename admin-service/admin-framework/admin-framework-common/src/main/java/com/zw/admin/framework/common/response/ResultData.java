package com.zw.admin.framework.common.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一数据响应结果集
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Data
@ToString
public class ResultData extends ResponseResult implements Serializable {

    private Object data;

    public ResultData() {
    }

    public ResultData(ResponseMain responseMain, Object object) {
        super(responseMain);
        this.data = object;
    }

}
