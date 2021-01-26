package com.zw.admin.system.controller;

import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.domain.entity.SysOperLog;
import com.zw.admin.system.service.OperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 新增操作日志
     * @param operLog 操作日志对象
     * @return 结果
     */
    @ApiOperation("新增操作日志")
    @PostMapping
    public AjaxResult add(@RequestBody SysOperLog operLog) {
        return toAjax(operationLogService.insertOperlog(operLog));
    }
}
