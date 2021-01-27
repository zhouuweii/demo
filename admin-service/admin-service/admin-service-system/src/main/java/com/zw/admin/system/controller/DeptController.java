package com.zw.admin.system.controller;

import com.zw.admin.framework.common.constant.UserConstants;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.core.annotation.Log;
import com.zw.admin.framework.core.annotation.PreAuthorize;
import com.zw.admin.framework.core.enums.BusinessType;
import com.zw.admin.framework.domain.entity.SysDept;
import com.zw.admin.system.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * 部门管理
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="部门管理",description = "提供部门管理相关操作")
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门管理数据
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @PreAuthorize(hasPermi = "system:dept:list")
    @ApiOperation("列表查询")
    @GetMapping("/list")
    public AjaxResult list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(depts);
    }

    /**
     * 查询部门列表
     * @param deptId 排除节点
     * @return 部门信息集合
     **/
    @PreAuthorize(hasPermi = "system:dept:list")
    @ApiOperation("查询部门列表（排除节点）")
    @GetMapping("/list/exclude/{deptId}")
    public AjaxResult excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        Iterator<SysDept> it = depts.iterator();
        while (it.hasNext()) {
            SysDept d = (SysDept) it.next();
            if (d.getDeptId().intValue() == deptId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + "")) {
                it.remove();
            }
        }
        return AjaxResult.success(depts);
    }

    /**
     * 根据部门ID查询信息
     * @param deptId 部门ID
     * @return 部门信息
     */
    @PreAuthorize(hasPermi = "system:dept:query")
    @ApiOperation("根据部门ID查询信息")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId) {
        return AjaxResult.success(deptService.selectDeptById(deptId));
    }

    /**
     * 构建前端所需要下拉树结构
     * @param dept 部门
     * @return 下拉树结构列表
     */
    @ApiOperation("构建前端所需要下拉树结构")
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 加载对应角色部门列表树
     * @param roleId 角色ID
     * @return 下拉树结构列表
     **/
    @ApiOperation("加载对应角色部门列表树")
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", deptService.buildDeptTreeSelect(depts));
        return ajax;
    }

    /**
     * 新增部门信息
     * @param dept 部门信息
     * @return 结果
     */
    @PreAuthorize(hasPermi = "system:dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增部门信息")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门信息
     * @param dept 部门信息
     * @return 结果
     */
    @PreAuthorize(hasPermi = "system:dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改部门信息")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        } else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0) {
            return AjaxResult.error("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门管理信息
     * @param deptId 部门ID
     * @return 结果
     */
    @PreAuthorize(hasPermi = "system:dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除部门管理信息")
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return AjaxResult.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return AjaxResult.error("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }
}
