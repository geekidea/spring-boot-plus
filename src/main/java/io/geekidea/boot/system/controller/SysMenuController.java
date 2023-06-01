package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysMenuAddDto;
import io.geekidea.boot.system.dto.SysMenuUpdateDto;
import io.geekidea.boot.system.query.SysMenuQuery;
import io.geekidea.boot.system.service.SysMenuService;
import io.geekidea.boot.system.vo.SysMenuInfoVo;
import io.geekidea.boot.system.vo.SysMenuVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统菜单 控制器
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@RestController
@RequestMapping("/sysMenu")
@Tag(name = "系统菜单")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 添加系统菜单
     *
     * @param sysMenuAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysMenu")
    @Operation(summary = "添加系统菜单")
    @Permission("sys:menu:add")
    public ApiResult addSysMenu(@Valid @RequestBody SysMenuAddDto sysMenuAddDto) throws Exception {
        boolean flag = sysMenuService.addSysMenu(sysMenuAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统菜单
     *
     * @param sysMenuUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysMenu")
    @Operation(summary = "修改系统菜单")
    @Permission("sys:menu:update")
    public ApiResult updateSysMenu(@Valid @RequestBody SysMenuUpdateDto sysMenuUpdateDto) throws Exception {
        boolean flag = sysMenuService.updateSysMenu(sysMenuUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统菜单
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysMenu/{id}")
    @Operation(summary = "删除系统菜单")
    @Permission("sys:menu:delete")
    public ApiResult deleteSysMenu(@PathVariable Long id) throws Exception {
        boolean flag = sysMenuService.deleteSysMenu(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统菜单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysMenu/{id}")
    @Operation(summary = "系统菜单详情")
    @Permission("sys:menu:info")
    public ApiResult<SysMenuInfoVo> getSysMenu(@PathVariable Long id) throws Exception {
        SysMenuInfoVo sysMenuInfoVo = sysMenuService.getSysMenuById(id);
        return ApiResult.success(sysMenuInfoVo);
    }

    /**
     * 系统菜单分页列表
     *
     * @param sysMenuQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysMenuList")
    @Operation(summary = "系统菜单分页列表")
    @Permission("sys:menu:list")
    public ApiResult<SysMenuVo> getSysMenuList(@Valid @RequestBody SysMenuQuery sysMenuQuery) throws Exception {
        Paging<SysMenuVo> paging = sysMenuService.getSysMenuList(sysMenuQuery);
        return ApiResult.success(paging);
    }

}
