package com.zw.admin.system.controller;

import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.domain.entity.SysMenu;
import com.zw.admin.framework.domain.vo.RouterVo;
import com.zw.admin.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags="菜单中心",description = "提供菜单相关操作")
@RestController
@RequestMapping("/menu")
public class MenuController {

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

    /**
     * 获取路由信息
     * @param
     * @return com.zw.admin.framework.common.response.ResultData
     **/
    @ApiOperation("获取路由信息Test")
    @GetMapping("getRoutersTest")
    public ResultData getRoutersTest(Long userId) {
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return new ResultData(CommonCode.SUCCESS, menus);
    }


}
