package com.zw.admin.auth.controller;

import com.zw.admin.auth.service.AuthService;
import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.domain.code.AuthCode;
import com.zw.admin.framework.domain.model.AuthToken;
import com.zw.admin.framework.domain.request.LoginBody;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户认证
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags = "登录", description = "提供登录")
@RestController
public class AuthController {

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResultData login(@RequestBody LoginBody form) {
        // 用户名或密码为空
        if (form == null || StringUtils.isEmpty(form.getUsername())) {
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if (form == null || StringUtils.isEmpty(form.getPassword())) {
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //申请令牌
        AuthToken authToken = authService.login(form.getUsername(), form.getPassword(), clientId, clientSecret);
        Map<String,Object> map =new HashMap<>();
        map.put("access_token", authToken.getJti());
        map.put("expires_in", authToken.getExpires_in());
        return new ResultData(CommonCode.SUCCESS, map);
    }

    @DeleteMapping("logout")
    public ResultData logout() {
        return new ResultData(CommonCode.SUCCESS, "SUCCESS");
    }
}
