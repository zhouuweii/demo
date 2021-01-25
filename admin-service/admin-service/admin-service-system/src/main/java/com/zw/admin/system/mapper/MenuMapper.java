package com.zw.admin.system.mapper;

import com.zw.admin.framework.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Mapper
public interface MenuMapper {

    /**
     * 根据用户ID查询权限
     * @param userId 用户ID
     * @return java.util.List<java.lang.String> 权限列表
     **/
    public List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 查询菜单
     * @return java.util.List<com.zw.admin.framework.domain.entity.SysMenu> 菜单列表
     **/
    public List<SysMenu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     * @param userId 用户ID
     * @return java.util.List<com.zw.admin.framework.domain.entity.SysMenu>
     **/
    public List<SysMenu> selectMenuTreeByUserId(Long userId);

}
