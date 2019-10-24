package io.geekidea.springbootplus.system.controller;

import io.geekidea.springbootplus.system.entity.SysRolePermission;
import io.geekidea.springbootplus.system.service.SysRolePermissionService;
import io.geekidea.springbootplus.system.param.SysRolePermissionQueryParam;
import io.geekidea.springbootplus.system.vo.SysRolePermissionQueryVo;
import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.common.param.IdParam;

/**
 * <pre>
 * 角色权限关系 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/sysRolePermission")
@Api("角色权限关系 API")
public class SysRolePermissionController extends BaseController {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 添加角色权限关系
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysRolePermission对象", notes = "添加角色权限关系", response = ApiResult.class)
    public ApiResult<Boolean> addSysRolePermission(@Valid @RequestBody SysRolePermission sysRolePermission) throws Exception {
            boolean flag = sysRolePermissionService.saveSysRolePermission(sysRolePermission);
            return ApiResult.result(flag);
    }

    /**
     * 修改角色权限关系
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysRolePermission对象", notes = "修改角色权限关系", response = ApiResult.class)
    public ApiResult<Boolean> updateSysRolePermission(@Valid @RequestBody SysRolePermission sysRolePermission) throws Exception {
            boolean flag = sysRolePermissionService.updateSysRolePermission(sysRolePermission);
            return ApiResult.result(flag);
    }

    /**
     * 删除角色权限关系
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除SysRolePermission对象", notes = "删除角色权限关系", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysRolePermission(@PathVariable("id") Long id) throws Exception {
            boolean flag = sysRolePermissionService.deleteSysRolePermission(id);
            return ApiResult.result(flag);
    }

    /**
     * 获取角色权限关系
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取SysRolePermission对象详情", notes = "查看角色权限关系", response = SysRolePermissionQueryVo.class)
    public ApiResult<SysRolePermissionQueryVo> getSysRolePermission(@PathVariable("id") Long id) throws Exception {
        SysRolePermissionQueryVo sysRolePermissionQueryVo = sysRolePermissionService.getSysRolePermissionById(id);
        return ApiResult.ok(sysRolePermissionQueryVo);
    }

    /**
     * 角色权限关系分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysRolePermission分页列表", notes = "角色权限关系分页列表", response = SysRolePermissionQueryVo.class)
    public ApiResult<Paging<SysRolePermissionQueryVo>> getSysRolePermissionPageList(@Valid @RequestBody SysRolePermissionQueryParam sysRolePermissionQueryParam) throws Exception {
        Paging<SysRolePermissionQueryVo> paging = sysRolePermissionService.getSysRolePermissionPageList(sysRolePermissionQueryParam);
        return ApiResult.ok(paging);
    }

}

