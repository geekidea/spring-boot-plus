/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.system.controller;

import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.common.controller.BaseController;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.system.entity.SysPermission;
import io.geekidea.springbootplus.framework.system.param.SysPermissionPageParam;
import io.geekidea.springbootplus.framework.system.service.SysPermissionService;
import io.geekidea.springbootplus.framework.system.service.SysRolePermissionService;
import io.geekidea.springbootplus.framework.system.vo.SysPermissionQueryVo;
import io.geekidea.springbootplus.framework.system.vo.SysPermissionTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 * 系统权限 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/sysPermission")
@Api(value = "系统权限 API", tags = {"系统权限"})
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 添加系统权限
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:permission:add")
    @ApiOperation(value = "添加SysPermission对象", notes = "添加系统权限", response = ApiResult.class)
    public ApiResult<Boolean> addSysPermission(@Validated @RequestBody SysPermission sysPermission) throws Exception {
        boolean flag = sysPermissionService.saveSysPermission(sysPermission);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统权限
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:permission:update")
    @ApiOperation(value = "修改SysPermission对象", notes = "修改系统权限", response = ApiResult.class)
    public ApiResult<Boolean> updateSysPermission(@Validated @RequestBody SysPermission sysPermission) throws Exception {
        boolean flag = sysPermissionService.updateSysPermission(sysPermission);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统权限
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:permission:delete")
    @ApiOperation(value = "删除SysPermission对象", notes = "删除系统权限", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysPermission(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysPermissionService.deleteSysPermission(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统权限
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:permission:info")
    @ApiOperation(value = "获取SysPermission对象详情", notes = "查看系统权限", response = SysPermissionQueryVo.class)
    public ApiResult<SysPermissionQueryVo> getSysPermission(@PathVariable("id") Long id) throws Exception {
        SysPermissionQueryVo sysPermissionQueryVo = sysPermissionService.getSysPermissionById(id);
        return ApiResult.ok(sysPermissionQueryVo);
    }

    /**
     * 系统权限分页列表
     */
    @PostMapping("/getPageList")
    @RequiresPermissions("sys:permission:page")
    @ApiOperation(value = "获取SysPermission分页列表", notes = "系统权限分页列表", response = SysPermissionQueryVo.class)
    public ApiResult<Paging<SysPermissionQueryVo>> getSysPermissionPageList(@Validated @RequestBody SysPermissionPageParam sysPermissionPageParam) throws Exception {
        Paging<SysPermissionQueryVo> paging = sysPermissionService.getSysPermissionPageList(sysPermissionPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取所有菜单列表
     * @return
     */
    @PostMapping("/getAllMenuList")
    @RequiresPermissions("sys:permission:all:menu:list")
    @ApiOperation(value = "获取所有菜单列表", notes = "获取所有菜单列表", response = SysPermission.class)
    public ApiResult<List<SysPermission>> getAllMenuList() throws Exception {
        List<SysPermission> list = sysPermissionService.getAllMenuList();
        return ApiResult.ok(list);
    }

    /**
     * 获取获取菜单树形列表
     * @return
     */
    @PostMapping("/getAllMenuTree")
    @RequiresPermissions("sys:permission:all:menu:tree")
    @ApiOperation(value = "获取所有菜单列表", notes = "获取所有菜单列表", response = SysPermissionTreeVo.class)
    public ApiResult<List<SysPermissionTreeVo>> getAllMenuTree() throws Exception {
        List<SysPermissionTreeVo> treeVos = sysPermissionService.getAllMenuTree();
        return ApiResult.ok(treeVos);
    }


    /**
     * 根据用户id获取菜单列表
     * @return
     */
    @PostMapping("/getMenuListByUserId/{userId}")
    @RequiresPermissions("sys:permission:menu:list")
    @ApiOperation(value = "根据用户id获取菜单列表", notes = "根据用户id获取菜单列表", response = SysPermission.class)
    public ApiResult<List<SysPermission>> getMenuListByUserId(@PathVariable("userId") Long userId) throws Exception {
        List<SysPermission> list = sysPermissionService.getMenuListByUserId(userId);
        return ApiResult.ok(list);
    }

    /**
     * 根据用户id获取菜单树形列表
     * @return
     */
    @PostMapping("/getMenuTreeByUserId/{userId}")
    @RequiresPermissions("sys:permission:menu:tree")
    @ApiOperation(value = "根据用户id获取菜单树形列表", notes = "根据用户id获取菜单树形列表", response = SysPermissionTreeVo.class)
    public ApiResult<List<SysPermissionTreeVo>> getMenuTreeByUserId(@PathVariable("userId") Long userId) throws Exception {
        List<SysPermissionTreeVo> treeVos = sysPermissionService.getMenuTreeByUserId(userId);
        return ApiResult.ok(treeVos);
    }

    /**
     * 根据用户id获取该用户所有权限编码
     * @return
     */
    @GetMapping("/getPermissionCodesByUserId/{userId}")
    @RequiresPermissions("sys:permission:codes")
    @ApiOperation(value = "根据用户id获取该用户所有权限编码", notes = "根据用户id获取该用户所有权限编码", response = ApiResult.class)
    public ApiResult<List<String>> getPermissionCodesByUserId(@PathVariable("userId") Long userId) throws Exception {
        List<String> list = sysPermissionService.getPermissionCodesByUserId(userId);
        return ApiResult.ok(list);
    }

    /**
     * 根据角色id获取该对应的所有三级权限ID
     * @return
     */
    @GetMapping("/getThreeLevelPermissionIdsByRoleId/{roleId}")
    // TODO
//    @RequiresPermissions("sys:permission:three-ids-by-role-id")
    @ApiOperation(value = "根据角色id获取该对应的所有三级权限ID", response = ApiResult.class)
    public ApiResult<List<Long>> getPermissionIdsByRoleId(@PathVariable("roleId") Long roleId) throws Exception {
        List<Long> list = sysRolePermissionService.getThreeLevelPermissionIdsByRoleId(roleId);
        return ApiResult.ok(list);
    }

    /**
     * 获取所有导航树形菜单(一级/二级菜单)
     * @return
     */
    @PostMapping("/getNavMenuTree")
    // TODO
//    @RequiresPermissions("sys:permission:nav-menu")
    @ApiOperation(value = "获取所有导航菜单(一级/二级菜单)", response = ApiResult.class)
    public ApiResult<List<SysPermissionTreeVo>> getNavMenuTree() throws Exception {
        List<SysPermissionTreeVo> list = sysPermissionService.getNavMenuTree();
        return ApiResult.ok(list);
    }

}

