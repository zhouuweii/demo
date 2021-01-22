package com.zw.admin.system.service;

import java.util.Set;

/**
 * 角色
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface RoleService {

    /**
     * 根据用户ID查询角色 用户ID
     * @param userId
     * @return java.util.Set<java.lang.String> 权限列表
     **/
    public Set<String> selectRolePermissionByUserId(Long userId);

}
