package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.*;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.system.vo.SysUserInfoVo;
import io.geekidea.boot.system.vo.SysUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统用户 控制器
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Tag(name = "系统用户")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 添加系统用户
     *
     * @param sysUserAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysUser")
    @Operation(summary = "添加系统用户")
    @Permission("sys:user:add")
    public ApiResult addSysUser(@Valid @RequestBody SysUserAddDto sysUserAddDto) throws Exception {
        boolean flag = sysUserService.addSysUser(sysUserAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统用户
     *
     * @param sysUserUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysUser")
    @Operation(summary = "修改系统用户")
    @Permission("sys:user:update")
    public ApiResult updateSysUser(@Valid @RequestBody SysUserUpdateDto sysUserUpdateDto) throws Exception {
        boolean flag = sysUserService.updateSysUser(sysUserUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysUser/{id}")
    @Operation(summary = "删除系统用户")
    @Permission("sys:user:delete")
    public ApiResult deleteSysUser(@PathVariable Long id) throws Exception {
        boolean flag = sysUserService.deleteSysUser(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统用户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysUser/{id}")
    @Operation(summary = "系统用户详情")
    @Permission("sys:user:info")
    public ApiResult<SysUserInfoVo> getSysUser(@PathVariable Long id) throws Exception {
        SysUserInfoVo sysUserInfoVo = sysUserService.getSysUserById(id);
        return ApiResult.success(sysUserInfoVo);
    }

    /**
     * 系统用户分页列表
     *
     * @param sysUserQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysUserList")
    @Operation(summary = "系统用户分页列表")
    @Permission("sys:user:list")
    public ApiResult<SysUserVo> getSysUserList(@Valid @RequestBody SysUserQuery sysUserQuery) throws Exception {
        Paging<SysUserVo> paging = sysUserService.getSysUserList(sysUserQuery);
        return ApiResult.success(paging);
    }

    /**
     * 重置系统用户密码
     *
     * @param sysUserResetPasswordDto
     * @return
     * @throws Exception
     */
    @PostMapping("/resetSysUserPassword")
    @Operation(summary = "重置系统用户密码")
    @Permission("sys:user:reset-password")
    public ApiResult resetSysUserPassword(@Valid @RequestBody SysUserResetPasswordDto sysUserResetPasswordDto) throws Exception {
        boolean flag = sysUserService.resetSysUserPassword(sysUserResetPasswordDto);
        return ApiResult.result(flag);
    }

    /**
     * 获取个人信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getProfile")
    @Operation(summary = "获取个人信息")
    public ApiResult<SysUserInfoVo> getProfile() throws Exception {
        Long userId = LoginUtil.getUserId();
        if (userId == null) {
            throw new BusinessException("用户ID为空");
        }
        SysUserInfoVo sysUserInfoVo = sysUserService.getSysUserById(userId);
        return ApiResult.success(sysUserInfoVo);
    }

    /**
     * 修改个人信息
     *
     * @param sysUserUpdateProfileDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateProfile")
    @Operation(summary = "修改个人信息")
    @Permission("sys:user:update-profile")
    public ApiResult updateProfile(@Valid @RequestBody SysUserUpdateProfileDto sysUserUpdateProfileDto) throws Exception {
        boolean flag = sysUserService.updateProfile(sysUserUpdateProfileDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改用户密码
     *
     * @param sysUserUpdatePasswordDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updatePassword")
    @Operation(summary = "修改用户密码")
    @Permission("sys:user:update-password")
    public ApiResult updatePassword(@Valid @RequestBody SysUserUpdatePasswordDto sysUserUpdatePasswordDto) throws Exception {
        boolean flag = sysUserService.updatePassword(sysUserUpdatePasswordDto);
        return ApiResult.result(flag);
    }

}
