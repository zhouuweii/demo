package com.zw.admin.system.mapper;

import com.zw.admin.framework.domain.entity.SysUser;

/**
 * 用户
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface UserMapper {

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return com.zw.admin.framework.domain.entity.SysUser 用户对象信息
     **/
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);
}
