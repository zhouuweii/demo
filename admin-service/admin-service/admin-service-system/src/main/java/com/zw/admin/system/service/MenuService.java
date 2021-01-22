package com.zw.admin.system.service;

import com.zw.admin.framework.domain.entity.SysMenu;
import com.zw.admin.framework.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * 菜单
 * @author: ZhouWei
 * @create: 2021-01
 **/
public interface MenuService {

    /**
     * 根据用户ID查询权限
     * @param userId 用户ID
     * @return 权限列表
     **/
    public Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     * @param userId 用户ID
     * @return 菜单列表
     **/
    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<SysMenu> menus);

}
