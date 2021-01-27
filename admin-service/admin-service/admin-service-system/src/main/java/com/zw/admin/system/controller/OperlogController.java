package com.zw.admin.system.controller;

import com.zw.admin.framework.common.utils.poi.ExcelUtil;
import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.common.web.page.TableDataInfo;
import com.zw.admin.framework.core.annotation.Log;
import com.zw.admin.framework.core.annotation.PreAuthorize;
import com.zw.admin.framework.core.enums.BusinessType;
import com.zw.admin.framework.domain.entity.SysOperLog;
import com.zw.admin.system.service.OperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 操作日志
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags = "操作日志", description = "提供操作日志相关操作")
@RestController
@RequestMapping("/operlog")
public class OperlogController extends BaseController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 查询系统操作日志集合
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @PreAuthorize(hasPermi = "system:operlog:list")
    @ApiOperation("查询系统操作日志集合")
    @GetMapping("/list")
    public TableDataInfo list(SysOperLog operLog) {
        startPage();
        List<SysOperLog> list = operationLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    /**
     * 新增操作日志
     * @param operLog 操作日志对象
     * @return 结果
     */
    @ApiOperation("新增操作日志")
    @PostMapping
    public AjaxResult add(@RequestBody SysOperLog operLog) {
        return toAjax(operationLogService.insertOperlog(operLog));
    }

    /**
     * 批量删除系统操作日志
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @PreAuthorize(hasPermi = "system:operlog:remove")
    @ApiOperation("批量删除系统操作日志")
    @DeleteMapping("/{operIds}")
    public AjaxResult remove(@PathVariable Long[] operIds) {
        return toAjax(operationLogService.deleteOperLogByIds(operIds));
    }

    /**
     * 清空操作日志
     */
    @PreAuthorize(hasPermi = "system:operlog:remove")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @ApiOperation("清空操作日志")
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        operationLogService.cleanOperLog();
        return AjaxResult.success();
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:operlog:export")
    @ApiOperation("导出操作日志")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOperLog operLog) throws IOException {
        List<SysOperLog> list = operationLogService.selectOperLogList(operLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
        util.exportExcel(response, list, "操作日志");
    }
}
