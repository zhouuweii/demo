package com.zw.admin.system.service.impl;

import com.zw.admin.framework.domain.entity.SysUser;
import com.zw.admin.system.mapper.UserMapper;
import com.zw.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return com.zw.admin.framework.domain.entity.SysUser 用户对象信息
     **/
    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 通过用户ID查询用户
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }
}
