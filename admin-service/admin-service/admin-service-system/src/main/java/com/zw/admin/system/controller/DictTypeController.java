package com.zw.admin.system.controller;

import com.github.pagehelper.PageInfo;
import com.zw.admin.framework.common.constant.UserConstants;
import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.common.response.TableResult;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.common.utils.poi.ExcelUtil;
import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.domain.entity.SysDictType;
import com.zw.admin.system.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 字典类型
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="字典类型",description = "提供字典类型相关操作")
@RestController
@RequestMapping("/dict/type")
public class DictTypeController extends BaseController {

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 根据条件分页查询字典类型
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     **/
//    @PreAuthorize(hasPermi = "system:dict:list")
    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResultData list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return new ResultData(CommonCode.SUCCESS, new TableResult(list,new PageInfo(list).getTotal()));
    }

    /**
     * 获取字典选择框列表
     * @return 所有字典类型集合信息
     **/
    @GetMapping("/optionselect")
    @ApiOperation("获取所有字典类型")
    public ResultData optionSelect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return new ResultData(CommonCode.SUCCESS, dictTypes);
    }

    /**
     * 根据字典类型ID查询信息
     * @param dictId 字典类型ID
     * @return 字典类型信息
     */
//    @PreAuthorize(hasPermi = "system:dict:query")
    @ApiOperation("查询详情-根据字典类型ID")
    @GetMapping(value = "/{dictId}")
    public ResultData getInfo(@PathVariable Long dictId) {
        SysDictType dictType = dictTypeService.selectDictTypeById(dictId);
        return new ResultData(CommonCode.SUCCESS, dictType);
    }

    /**
     * 新增字典类型信息
     * @param dict 字典类型信息
     * @return 结果
     */
//    @PreAuthorize(hasPermi = "system:dict:add")
//    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @ApiOperation("新增字典类型")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictType dict) {
        if(!dictTypeService.checkDictTypeUnique(dict)){
            ExceptionCast.cast(CommonCode.DATA_ALREADY_EXISTS_DIC);
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改保存字典类型信息
     * @param dict 字典类型信息
     * @return 结果
     */
//    @PreAuthorize(hasPermi = "system:dict:edit")
//    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @ApiOperation("修改保存字典类型信息")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            ExceptionCast.cast(CommonCode.DATA_ALREADY_EXISTS_DIC_UPDATE);
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 批量删除字典信息
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
//    @PreAuthorize(hasPermi = "system:dict:remove")
//    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @ApiOperation("批量删除字典信息")
    @DeleteMapping("/{dictIds}")
    public AjaxResult remove(@PathVariable Long[] dictIds) {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 清空缓存
     */
//    @PreAuthorize(hasPermi = "system:dict:remove")
//    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @ApiOperation("清空缓存数据")
    @DeleteMapping("/clearCache")
    public AjaxResult clearCache() {
        dictTypeService.clearCache();
        return AjaxResult.success();
    }

    //    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
//    @PreAuthorize(hasPermi = "system:dict:export")
    @ApiOperation("导出数据")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictType dictType) throws IOException {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        util.exportExcel(response, list, "字典类型");
    }
}
