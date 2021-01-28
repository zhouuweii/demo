package com.zw.admin.system.service;

import java.util.Map;

/**
 * 验证码相关
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface VerifyService {

    /**
     * 获取验证码
     * @param type 验证码类型
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    Map<String,String> getCodeChar (String type);

    /**
     * 获取手机短信验证码
     * @param phone 手机号码
     * @return 验证码唯一标识
     **/
    public String getSmsCode(String phone);

    /**
     * 校验手机短信验证码
     * @param phone 手机号码
     * @param code 手机验证码
     * @param smsId 验证码唯一标识
     * @return java.lang.Boolean
     **/
    public Boolean checkSmsCode(String phone,String code,String smsId);
}
