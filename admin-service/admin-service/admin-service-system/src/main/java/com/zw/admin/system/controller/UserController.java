package com.zw.admin.system.controller;

import com.zw.admin.framework.common.constant.CacheConstants;
import com.zw.admin.framework.common.constant.UserConstants;
import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.common.utils.SecurityUtils;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.framework.common.utils.poi.ExcelUtil;
import com.zw.admin.framework.common.web.controller.BaseController;
import com.zw.admin.framework.common.web.domain.AjaxResult;
import com.zw.admin.framework.common.web.page.TableDataInfo;
import com.zw.admin.framework.core.annotation.Log;
import com.zw.admin.framework.core.annotation.PreAuthorize;
import com.zw.admin.framework.core.enums.BusinessType;
import com.zw.admin.framework.core.service.RedisTemplateUtil;
import com.zw.admin.framework.domain.entity.SysRole;
import com.zw.admin.framework.domain.entity.SysUser;
import com.zw.admin.framework.domain.model.LoginUser;
import com.zw.admin.framework.domain.request.Register;
import com.zw.admin.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户管理
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Api(tags = "用户管理", description = "提供用户管理相关操作")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PostService postService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @PreAuthorize(hasPermi = "system:user:list")
    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 获取用户详细信息
     * @param username 用户名
     * @return com.zw.admin.framework.common.response.ResultData
     **/
    @ApiOperation("获取用户详细信息")
    @GetMapping("/getInfo/{username}")
    public ResultData getInfo(@PathVariable("username") String username) {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser)) {
            ExceptionCast.cast(CommonCode.DATA_NOT_EXISTS_USER);
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser.getUserId());
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser.getUserId());
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return new ResultData(CommonCode.SUCCESS, sysUserVo);
    }

    /**
     * 获取用户详细信息
     * @return com.zw.admin.framework.common.response.ResultData
     **/
    @ApiOperation("获取用户详细信息")
    @GetMapping("/getInfoByToken")
    public ResultData getInfoByToken() {
        Long userId = SecurityUtils.getUserId();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        // 用户信息
        SysUser sysUser = userService.selectUserById(userId);
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return new ResultData(CommonCode.SUCCESS, sysUserVo);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:user:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUser user) throws IOException {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize(hasPermi = "system:user:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.importTemplateExcel(response, "用户数据");
    }

    @PreAuthorize(hasPermi = "system:user:query")
    @ApiOperation("根据用户编号获取详细信息")
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    @PreAuthorize(hasPermi = "system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增用户")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && !userService.checkPhoneUnique(user.getPhonenumber())) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setPassword(user.getPassword());
        return toAjax(userService.insertUser(user));
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResultData register(@Validated @RequestBody Register register) {
        //校验验证码
        Boolean success = verifyService.checkSmsCode(register.getPhone(), register.getCode(), register.getSmsId());
        redisTemplateUtil.deleteObject(CacheConstants.SMS_CODE_KEY + register.getPhone() + "-" + register.getSmsId());
        if(success){
            //封装用户信息
            SysUser sysUser =new SysUser();
            sysUser.setUserName(register.getUsername());
            sysUser.setPassword(SecurityUtils.encryptPassword(register.getPassword()));
            sysUser.setPhonenumber(register.getPhone());
            sysUser.setCreateBy("自主注册");
            sysUser.setStatus("0");
            //新增用户
            userService.insertUser(sysUser);
            return new ResultData(CommonCode.SUCCESS,sysUser);
        }else{
            ExceptionCast.cast(CommonCode.DATA_PHONE_CODE_ERROR);
        }
        ExceptionCast.cast(CommonCode.REGISTER_ERROR);
        return null;
    }

    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改用户")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && !userService.checkPhoneUnique(user.getPhonenumber())) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    @PreAuthorize(hasPermi = "system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除用户")
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }


    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation("重置密码")
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @ApiOperation("状态修改")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }
}

