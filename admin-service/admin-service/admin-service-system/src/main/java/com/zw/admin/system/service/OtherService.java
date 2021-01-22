package com.zw.admin.system.service;

import java.util.Map;

/**
 * 其他接口
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface OtherService {

    /**
     * 获取验证码
     * @param type 验证码类型
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    Map<String,String> getCodeChar (String type);

}
