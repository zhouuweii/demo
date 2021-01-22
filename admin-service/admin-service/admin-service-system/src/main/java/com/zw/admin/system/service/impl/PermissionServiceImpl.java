package com.zw.admin.system.service.impl;

import com.zw.admin.framework.domain.entity.SysUser;
import com.zw.admin.system.service.MenuService;
import com.zw.admin.system.service.PermissionService;
import com.zw.admin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取角色数据权限
     * @param userId 用户Id
     * @return java.util.Set<java.lang.String> 角色权限信息
     **/
    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId)) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * @param userId 用户Id
     * @return java.util.Set<java.lang.String> 菜单权限信息
     **/
    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }
}
