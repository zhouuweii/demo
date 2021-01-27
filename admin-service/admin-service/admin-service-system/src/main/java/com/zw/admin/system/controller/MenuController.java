package com.zw.admin.system.controller;

import com.zw.admin.framework.common.constant.Constants;
import com.zw.admin.framework.common.constant.UserConstants;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.core.annotation.Log;
import com.zw.admin.framework.core.annotation.PreAuthorize;
import com.zw.admin.framework.core.enums.BusinessType;
import com.zw.admin.framework.domain.entity.SysMenu;
import com.zw.admin.framework.domain.vo.RouterVo;
import com.zw.admin.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags = "菜单管理", description = "提供菜单管理相关操作")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取路由信息
     * @param
     * @return com.zw.admin.framework.common.response.ResultData
     **/
    @ApiOperation("获取路由信息")
    @GetMapping("getRouters")
    public ResultData getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        List<RouterVo> routers = menuService.buildMenus(menus);
        return new ResultData(CommonCode.SUCCESS, routers);
    }

    //TODO DELETE
//    /**
//     * 获取路由信息-根据用户ID
//     * @param
//     * @return com.zw.admin.framework.common.response.ResultData
//     **/
//    @ApiOperation("获取路由信息-根据用户ID")
//    @GetMapping("getRoutersById")
//    public ResultData getRoutersById(Long userId) {
//        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
//        return new ResultData(CommonCode.SUCCESS, menus);
//    }


    @PreAuthorize(hasPermi = "system:menu:list")
    @ApiOperation("获取菜单列表")
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return AjaxResult.success(menus);
    }

    @PreAuthorize(hasPermi = "system:menu:query")
    @ApiOperation("根据菜单编号获取详细信息")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    @ApiOperation("获取菜单下拉树列表")
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenu menu) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }

    @ApiOperation("加载对应角色菜单列表树")
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    @PreAuthorize(hasPermi = "system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增菜单")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame())
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    @PreAuthorize(hasPermi = "system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改菜单")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame())
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    @PreAuthorize(hasPermi = "system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除菜单")
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return AjaxResult.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return AjaxResult.error("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }
}
