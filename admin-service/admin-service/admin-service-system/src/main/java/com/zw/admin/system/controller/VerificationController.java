package com.zw.admin.system.controller;

import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.system.service.OtherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 验证
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="验证",description = "提供验证码等相关操作")
@RestController
@RequestMapping("/verification")
public class VerificationController {

    @Autowired
    private OtherService otherService;

    @ApiOperation("获取验证码")
    @GetMapping("/getCodeChar")
    public ResultData getCode(){
        Map<String, String> map = otherService.getCodeChar("");
        return new ResultData(CommonCode.SUCCESS,map);
    }

}
