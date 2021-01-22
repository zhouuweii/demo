package com.zw.admin.system.service;

import java.util.Set;

/**
 * 权限
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface PermissionService {

    /**
     * 获取角色数据权限
     * @param userId 用户Id
     * @return java.util.Set<java.lang.String> 角色权限信息
     **/
    public Set<String> getRolePermission(Long userId);

    /**
     * 获取菜单数据权限
     * @param userId 用户Id
     * @return java.util.Set<java.lang.String> 菜单权限信息
     **/
    public Set<String> getMenuPermission(Long userId);

}
