package com.zw.admin.system.service.impl;

import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.framework.domain.entity.SysRole;
import com.zw.admin.system.mapper.RoleMapper;
import com.zw.admin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据用户ID查询角色 用户ID
     * @param userId
     * @return java.util.Set<java.lang.String> 权限列表
     **/
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

}
