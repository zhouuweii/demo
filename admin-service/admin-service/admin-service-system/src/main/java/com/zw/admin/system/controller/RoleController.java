package com.zw.admin.system.controller;

import com.zw.admin.framework.common.constant.UserConstants;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.common.utils.poi.ExcelUtil;
import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.common.web.page.TableDataInfo;
import com.zw.admin.framework.core.annotation.Log;
import com.zw.admin.framework.core.annotation.PreAuthorize;
import com.zw.admin.framework.core.enums.BusinessType;
import com.zw.admin.framework.domain.entity.SysRole;
import com.zw.admin.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 角色管理
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags = "角色管理", description = "提供角色管理相关操作")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize(hasPermi = "system:role:list")
    @ApiOperation("查询列表")
    @GetMapping("/list")
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "system:role:query")
    @ApiOperation("根据角色编号获取详细信息")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId) {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    @PreAuthorize(hasPermi = "system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增角色")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysRole role) {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(SecurityUtils.getUsername());
        return toAjax(roleService.insertRole(role));

    }

    @PreAuthorize(hasPermi = "system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改保存角色")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRole(role));
    }

    @PreAuthorize(hasPermi = "system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改保存数据权限")
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        return toAjax(roleService.authDataScope(role));
    }

    @PreAuthorize(hasPermi = "system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @ApiOperation("状态修改")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRoleStatus(role));
    }

    @PreAuthorize(hasPermi = "system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除角色")
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    @PreAuthorize(hasPermi = "system:role:query")
    @ApiOperation("获取角色选择框列表")
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        return AjaxResult.success(roleService.selectRoleAll());
    }

    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:role:export")
    @ApiOperation("导出数据")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRole role) throws IOException {
        List<SysRole> list = roleService.selectRoleList(role);
        ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
        util.exportExcel(response, list, "角色数据");
    }
}