package com.zw.admin.system.controller;

import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.domain.code.AuthCode;
import com.zw.admin.system.service.UserService;
import com.zw.admin.system.service.VerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 验证中心
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="验证中心",description = "提供验证码等相关操作")
@RestController
@RequestMapping("/verification")
public class VerifyController {

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private UserService userService;

    @ApiOperation("获取验证码")
    @GetMapping("/getCodeChar")
    public ResultData getCode(){
        Map<String, String> map = verifyService.getCodeChar("");
        return new ResultData(CommonCode.SUCCESS,map);
    }

    /**
     * 获取手机短信验证码-注册账号
     * @param phone 手机号码
     * @return 验证码唯一标识
     **/
    @ApiOperation("获取手机短信验证码-注册账号")
    @GetMapping("/getRegisterSmsCode")
    public ResultData getRegisterSmsCode(String phone){
        Boolean exist = userService.checkPhoneUnique(phone);
        if (!exist){
            String smsId = verifyService.getSmsCode(phone);
            return new ResultData(CommonCode.SUCCESS,smsId);
        }
        ExceptionCast.cast(CommonCode.DATA_EXISTS_USER_PHONE);
        return null;
    }

    /**
     * 获取手机短信验证码
     * @param phone 手机号码
     * @return 验证码唯一标识
     **/
    @ApiOperation("获取手机短信验证码")
    @GetMapping("/getSmsCode")
    public ResultData getSmsCode(String phone){
        String smsId = verifyService.getSmsCode(phone);
        return new ResultData(CommonCode.SUCCESS,smsId);
    }

    /**
     * 校验手机短信验证码
     * @param phone 手机号码
     * @param code 手机验证码
     * @param smsId 验证码唯一标识
     * @return java.lang.Boolean
     **/
    @ApiOperation("校验手机短信验证码")
    @GetMapping("/checkSmsCode")
    public ResultData checkSmsCode(String phone,String code,String smsId){
        Boolean result = verifyService.checkSmsCode(phone, code, smsId);
        return new ResultData(CommonCode.SUCCESS,result);
    }
}
