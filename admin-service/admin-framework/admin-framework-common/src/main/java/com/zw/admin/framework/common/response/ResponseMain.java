package com.zw.admin.framework.common.response;

/**
 * 响应结果集主体字段
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface ResponseMain {

    /**
     *
     * 操作是否成功,true为成功，false操作失败
     * @return boolean
     **/
    boolean success();

    /**
     * 操作代码
     * @return int
     **/
    int code();

    /**
     * 提示信息
     * @return java.lang.String
     **/
    String message();

}
