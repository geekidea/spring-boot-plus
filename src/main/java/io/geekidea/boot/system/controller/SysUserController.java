package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysUserDto;
import io.geekidea.boot.system.dto.SysUserResetPasswordDto;
import io.geekidea.boot.system.dto.SysUserUpdatePasswordDto;
import io.geekidea.boot.system.dto.SysUserUpdateProfileDto;
import io.geekidea.boot.system.query.SysUserQuery;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.system.vo.SysUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统用户 控制器
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@RestController
@Tag(name = "系统用户")
@RequestMapping("/admin/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 添加系统用户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysUser")
    @Operation(summary = "添加系统用户")
    @Permission("sys:user:add")
    public ApiResult addSysUser(@Valid @RequestBody SysUserDto dto) {
        boolean flag = sysUserService.addSysUser(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统用户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysUser")
    @Operation(summary = "修改系统用户")
    @Permission("sys:user:update")
    public ApiResult updateSysUser(@Valid @RequestBody SysUserDto dto) {
        boolean flag = sysUserService.updateSysUser(dto);
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
    public ApiResult deleteSysUser(@PathVariable Long id) {
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
    public ApiResult<SysUserVo> getSysUser(@PathVariable Long id) {
        SysUserVo sysUserVo = sysUserService.getSysUserById(id);
        return ApiResult.success(sysUserVo);
    }

    /**
     * 系统用户分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysUserPage")
    @Operation(summary = "系统用户分页列表")
    @Permission("sys:user:page")
    public ApiResult<SysUserVo> getSysUserPage(@Valid @RequestBody SysUserQuery query) {
        Paging<SysUserVo> paging = sysUserService.getSysUserPage(query);
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
    public ApiResult resetSysUserPassword(@Valid @RequestBody SysUserResetPasswordDto sysUserResetPasswordDto) {
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
    public ApiResult<SysUserVo> getProfile() {
        Long userId = LoginUtil.getUserId();
        if (userId == null) {
            throw new BusinessException("用户ID为空");
        }
        SysUserVo sysUserVo = sysUserService.getSysUserById(userId);
        return ApiResult.success(sysUserVo);
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
    public ApiResult updateProfile(@Valid @RequestBody SysUserUpdateProfileDto sysUserUpdateProfileDto) {
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
    public ApiResult updatePassword(@Valid @RequestBody SysUserUpdatePasswordDto sysUserUpdatePasswordDto) {
        boolean flag = sysUserService.updatePassword(sysUserUpdatePasswordDto);
        return ApiResult.result(flag);
    }

    /**
     * 导入Excel用户数据
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @PostMapping("/importExcel")
    @Operation(summary = "导入Excel用户数据")
    public ApiResult importExcel(MultipartFile multipartFile) throws Exception {
        boolean flag = sysUserService.importExcel(multipartFile);
        return ApiResult.result(flag);
    }

}
