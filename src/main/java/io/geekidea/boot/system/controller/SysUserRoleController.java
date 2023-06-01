package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysUserRoleAddDto;
import io.geekidea.boot.system.dto.SysUserRoleUpdateDto;
import io.geekidea.boot.system.query.SysUserRoleQuery;
import io.geekidea.boot.system.service.SysUserRoleService;
import io.geekidea.boot.system.vo.SysUserRoleInfoVo;
import io.geekidea.boot.system.vo.SysUserRoleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户角色关系表 控制器
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@RestController
@RequestMapping("/sysUserRole")
@Tag(name = "用户角色关系表")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 添加用户角色关系表
     *
     * @param sysUserRoleAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysUserRole")
    @Operation(summary = "添加用户角色关系表")
    @Permission("sys:user:role:add")
    public ApiResult addSysUserRole(@Valid @RequestBody SysUserRoleAddDto sysUserRoleAddDto) throws Exception {
        boolean flag = sysUserRoleService.addSysUserRole(sysUserRoleAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改用户角色关系表
     *
     * @param sysUserRoleUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysUserRole")
    @Operation(summary = "修改用户角色关系表")
    @Permission("sys:user:role:update")
    public ApiResult updateSysUserRole(@Valid @RequestBody SysUserRoleUpdateDto sysUserRoleUpdateDto) throws Exception {
        boolean flag = sysUserRoleService.updateSysUserRole(sysUserRoleUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除用户角色关系表
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysUserRole/{id}")
    @Operation(summary = "删除用户角色关系表")
    @Permission("sys:user:role:delete")
    public ApiResult deleteSysUserRole(@PathVariable Long id) throws Exception {
        boolean flag = sysUserRoleService.deleteSysUserRole(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取用户角色关系表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysUserRole/{id}")
    @Operation(summary = "用户角色关系表详情")
    @Permission("sys:user:role:info")
    public ApiResult<SysUserRoleInfoVo> getSysUserRole(@PathVariable Long id) throws Exception {
        SysUserRoleInfoVo sysUserRoleInfoVo = sysUserRoleService.getSysUserRoleById(id);
        return ApiResult.success(sysUserRoleInfoVo);
    }

    /**
     * 用户角色关系表分页列表
     *
     * @param sysUserRoleQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysUserRoleList")
    @Operation(summary = "用户角色关系表分页列表")
    @Permission("sys:user:role:list")
    public ApiResult<SysUserRoleVo> getSysUserRoleList(@Valid @RequestBody SysUserRoleQuery sysUserRoleQuery) throws Exception {
        Paging<SysUserRoleVo> paging = sysUserRoleService.getSysUserRoleList(sysUserRoleQuery);
        return ApiResult.success(paging);
    }

}
