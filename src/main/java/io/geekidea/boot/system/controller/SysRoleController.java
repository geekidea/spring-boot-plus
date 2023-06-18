package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.RoleMenusDto;
import io.geekidea.boot.system.dto.SysRoleAddDto;
import io.geekidea.boot.system.dto.SysRoleUpdateDto;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.query.SysRoleQuery;
import io.geekidea.boot.system.service.SysRoleService;
import io.geekidea.boot.system.vo.SysRoleInfoVo;
import io.geekidea.boot.system.vo.SysRoleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统角色 控制器
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")
@Tag(name = "系统角色")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加系统角色
     *
     * @param sysRoleAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysRole")
    @Operation(summary = "添加系统角色")
    @Permission("sys:role:add")
    public ApiResult addSysRole(@Valid @RequestBody SysRoleAddDto sysRoleAddDto) throws Exception {
        boolean flag = sysRoleService.addSysRole(sysRoleAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统角色
     *
     * @param sysRoleUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysRole")
    @Operation(summary = "修改系统角色")
    @Permission("sys:role:update")
    public ApiResult updateSysRole(@Valid @RequestBody SysRoleUpdateDto sysRoleUpdateDto) throws Exception {
        boolean flag = sysRoleService.updateSysRole(sysRoleUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysRole/{id}")
    @Operation(summary = "删除系统角色")
    @Permission("sys:role:delete")
    public ApiResult deleteSysRole(@PathVariable Long id) throws Exception {
        boolean flag = sysRoleService.deleteSysRole(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRole/{id}")
    @Operation(summary = "系统角色详情")
    @Permission("sys:role:info")
    public ApiResult<SysRoleInfoVo> getSysRole(@PathVariable Long id) throws Exception {
        SysRoleInfoVo sysRoleInfoVo = sysRoleService.getSysRoleById(id);
        return ApiResult.success(sysRoleInfoVo);
    }

    /**
     * 系统角色分页列表
     *
     * @param sysRoleQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRoleList")
    @Operation(summary = "系统角色分页列表")
    @Permission("sys:role:list")
    public ApiResult<SysRoleVo> getSysRoleList(@Valid @RequestBody SysRoleQuery sysRoleQuery) throws Exception {
        Paging<SysRoleVo> paging = sysRoleService.getSysRoleList(sysRoleQuery);
        return ApiResult.success(paging);
    }

    /**
     * 系统所有角色列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRoleAllList")
    @Operation(summary = "系统所有角色列表")
    @Permission("sys:role:all-list")
    public ApiResult<SysRole> getSysRoleAllList() throws Exception {
        List<SysRole> list = sysRoleService.getSysRoleAllList();
        return ApiResult.success(list);
    }

    /**
     * 设置角色权限
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/setRoleMenus")
    @Operation(summary = "设置角色权限")
    @Permission("sys:role:set-role-menus")
    public ApiResult<SysRole> setRoleMenus(@Valid @RequestBody RoleMenusDto roleMenusDto) throws Exception {
        boolean flag = sysRoleService.setRoleMenus(roleMenusDto);
        return ApiResult.success(flag);
    }

}
