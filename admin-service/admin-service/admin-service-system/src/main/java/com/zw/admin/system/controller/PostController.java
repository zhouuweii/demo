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
import com.zw.admin.framework.core.annotation.Log;
import com.zw.admin.framework.core.annotation.PreAuthorize;
import com.zw.admin.framework.core.enums.BusinessType;
import com.zw.admin.framework.domain.entity.SysPost;
import com.zw.admin.system.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 岗位信息
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="岗位管理",description = "提供岗位管理相关操作")
@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    /**
     * 查询岗位信息集合
     * @param post 岗位信息
     * @return 岗位列表
     */
    @PreAuthorize(hasPermi = "system:post:list")
    @ApiOperation("列表查询")
    @GetMapping("/list")
    public ResultData list(SysPost post) {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return new ResultData(CommonCode.SUCCESS, new TableResult(list,new PageInfo(list).getTotal()));
    }

    /**
     * 查询所有岗位
     * @return 岗位列表
     */
    @ApiOperation("查询所有岗位")
    @GetMapping("/optionselect")
    public ResultData optionselect() {
        List<SysPost> posts = postService.selectPostAll();
        return new ResultData(CommonCode.SUCCESS, posts);
    }

    /**
     * 通过岗位ID查询岗位信息
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @PreAuthorize(hasPermi = "system:post:query")
    @ApiOperation("通过岗位ID查询岗位信息")
    @GetMapping(value = "/{postId}")
    public ResultData getInfo(@PathVariable Long postId) {
        return new ResultData(CommonCode.SUCCESS, postService.selectPostById(postId));
    }

    /**
     * 新增岗位信息
     * @param post 岗位信息
     * @return 结果
     */
    @PreAuthorize(hasPermi = "system:post:add")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增岗位信息")
    @PostMapping
    public ResultData add(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            ExceptionCast.cast(CommonCode.DATA_ALREADY_EXISTS_POST);
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            ExceptionCast.cast(CommonCode.DATA_ALREADY_EXISTS_POSTCODE);
        }
        post.setCreateBy(SecurityUtils.getUsername());
        return new ResultData(CommonCode.SUCCESS, postService.insertPost(post));
    }

    /**
     * 修改岗位信息
     * @param post 岗位信息
     * @return 结果
     */
    @PreAuthorize(hasPermi = "system:post:edit")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改岗位信息")
    @PutMapping
    public ResultData edit(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            ExceptionCast.cast(CommonCode.DATA_ALREADY_EXISTS_POST_UPDATE);
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            ExceptionCast.cast(CommonCode.DATA_ALREADY_EXISTS_POST_CODE_UPDATE);
        }
        post.setUpdateBy(SecurityUtils.getUsername());
        return new ResultData(CommonCode.SUCCESS, postService.updatePost(post));
    }

    /**
     * 批量删除岗位信息
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    @PreAuthorize(hasPermi = "system:post:remove")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @ApiOperation("批量删除岗位信息")
    @DeleteMapping("/{postIds}")
    public ResultData remove(@PathVariable Long[] postIds) {
        return new ResultData(CommonCode.SUCCESS, postService.deletePostByIds(postIds));
    }

    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:post:export")
    @ApiOperation("导出数据")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPost post) throws IOException {
        List<SysPost> list = postService.selectPostList(post);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        util.exportExcel(response, list, "岗位数据");
    }

}
