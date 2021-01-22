package com.zw.admin.system.controller;

import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.framework.domain.entity.SysUser;
import com.zw.admin.framework.domain.model.LoginUser;
import com.zw.admin.system.service.PermissionService;
import com.zw.admin.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 用户
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="用户中心",description = "提供用户相关操作")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取用户详细信息
     * @param username 用户名
     * @return com.zw.admin.framework.common.response.ResultData
     **/
    @ApiOperation("获取用户详细信息")
    @GetMapping("/getInfo/{username}")
    public ResultData getInfo(@PathVariable("username") String username) {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser)) {
            ExceptionCast.cast(CommonCode.DATA_NOT_EXISTS_USER);
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser.getUserId());
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser.getUserId());
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return new ResultData(CommonCode.SUCCESS, sysUserVo);
    }

    /**
     * 获取用户详细信息
     * @return com.zw.admin.framework.common.response.ResultData
     **/
    @ApiOperation("获取用户详细信息")
    @GetMapping("/getInfoByToken")
    public ResultData getInfoByToken() {
        Long userId = SecurityUtils.getUserId();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        // 用户信息
        SysUser sysUser = userService.selectUserById(userId);
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return new ResultData(CommonCode.SUCCESS, sysUserVo);
    }
}
