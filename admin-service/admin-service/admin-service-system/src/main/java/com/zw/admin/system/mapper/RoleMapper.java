package com.zw.admin.system.mapper;

import com.zw.admin.framework.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Mapper
public interface RoleMapper {

    /**
     * 根据用户ID查询角色
     * @param userId 用户ID
     * @return java.util.List<com.zw.admin.framework.domain.entity.SysRole> 角色列表
     **/
    public List<SysRole> selectRolePermissionByUserId(Long userId);

}
