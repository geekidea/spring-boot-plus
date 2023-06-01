package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysRoleMenuAddDto;
import io.geekidea.boot.system.dto.SysRoleMenuUpdateDto;
import io.geekidea.boot.system.query.SysRoleMenuQuery;
import io.geekidea.boot.system.service.SysRoleMenuService;
import io.geekidea.boot.system.vo.SysRoleMenuInfoVo;
import io.geekidea.boot.system.vo.SysRoleMenuVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色菜单关系表 控制器
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@RestController
@RequestMapping("/sysRoleMenu")
@Tag(name = "角色菜单关系表")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 添加角色菜单关系表
     *
     * @param sysRoleMenuAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysRoleMenu")
    @Operation(summary = "添加角色菜单关系表")
    @Permission("sys:role:menu:add")
    public ApiResult addSysRoleMenu(@Valid @RequestBody SysRoleMenuAddDto sysRoleMenuAddDto) throws Exception {
        boolean flag = sysRoleMenuService.addSysRoleMenu(sysRoleMenuAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改角色菜单关系表
     *
     * @param sysRoleMenuUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysRoleMenu")
    @Operation(summary = "修改角色菜单关系表")
    @Permission("sys:role:menu:update")
    public ApiResult updateSysRoleMenu(@Valid @RequestBody SysRoleMenuUpdateDto sysRoleMenuUpdateDto) throws Exception {
        boolean flag = sysRoleMenuService.updateSysRoleMenu(sysRoleMenuUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除角色菜单关系表
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysRoleMenu/{id}")
    @Operation(summary = "删除角色菜单关系表")
    @Permission("sys:role:menu:delete")
    public ApiResult deleteSysRoleMenu(@PathVariable Long id) throws Exception {
        boolean flag = sysRoleMenuService.deleteSysRoleMenu(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取角色菜单关系表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRoleMenu/{id}")
    @Operation(summary = "角色菜单关系表详情")
    @Permission("sys:role:menu:info")
    public ApiResult<SysRoleMenuInfoVo> getSysRoleMenu(@PathVariable Long id) throws Exception {
        SysRoleMenuInfoVo sysRoleMenuInfoVo = sysRoleMenuService.getSysRoleMenuById(id);
        return ApiResult.success(sysRoleMenuInfoVo);
    }

    /**
     * 角色菜单关系表分页列表
     *
     * @param sysRoleMenuQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRoleMenuList")
    @Operation(summary = "角色菜单关系表分页列表")
    @Permission("sys:role:menu:list")
    public ApiResult<SysRoleMenuVo> getSysRoleMenuList(@Valid @RequestBody SysRoleMenuQuery sysRoleMenuQuery) throws Exception {
        Paging<SysRoleMenuVo> paging = sysRoleMenuService.getSysRoleMenuList(sysRoleMenuQuery);
        return ApiResult.success(paging);
    }

}
