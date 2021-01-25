package com.zw.admin.system.controller;

import com.github.pagehelper.PageInfo;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.common.response.TableResult;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.framework.common.utils.poi.ExcelUtil;
import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.domain.entity.SysDictData;
import com.zw.admin.system.service.DictDataService;
import com.zw.admin.system.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="字典数据",description = "提供字典数据相关操作")
@RestController
@RequestMapping("/dict/data")
public class DictDataController extends BaseController {

    @Autowired
    private DictDataService dictDataService;

    @Autowired
    private DictTypeService dictTypeService;

//    @PreAuthorize(hasPermi = "system:dict:list")
    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResultData list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return new ResultData(CommonCode.SUCCESS, new TableResult(list,new PageInfo(list).getTotal()));
    }

//    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
//    @PreAuthorize(hasPermi = "system:dict:export")
    @ApiOperation("导出数据")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData) throws IOException {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
//    @PreAuthorize(hasPermi = "system:dict:query")
    @ApiOperation("根据字典数据ID查询信息")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @ApiOperation("根据字典类型查询字典数据")
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 新增字典类型
     */
//    @PreAuthorize(hasPermi = "system:dict:add")
//    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @ApiOperation("新增保存字典数据信息")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict) {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
//    @PreAuthorize(hasPermi = "system:dict:edit")
//    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @ApiOperation("修改保存字典数据信息")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
//    @PreAuthorize(hasPermi = "system:dict:remove")
//    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @ApiOperation("批量删除字典数据信息")
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes) {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }
}
