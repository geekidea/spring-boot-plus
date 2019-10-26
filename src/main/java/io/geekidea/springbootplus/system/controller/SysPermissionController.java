/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.system.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.controller.BaseController;
import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.system.entity.SysPermission;
import io.geekidea.springbootplus.system.param.SysPermissionQueryParam;
import io.geekidea.springbootplus.system.service.SysPermissionService;
import io.geekidea.springbootplus.system.vo.SysPermissionQueryVo;
import io.geekidea.springbootplus.system.vo.SysPermissionTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Api("系统权限 API")
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 添加系统权限
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysPermission对象", notes = "添加系统权限", response = ApiResult.class)
    public ApiResult<Boolean> addSysPermission(@Valid @RequestBody SysPermission sysPermission) throws Exception {
        boolean flag = sysPermissionService.saveSysPermission(sysPermission);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统权限
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysPermission对象", notes = "修改系统权限", response = ApiResult.class)
    public ApiResult<Boolean> updateSysPermission(@Valid @RequestBody SysPermission sysPermission) throws Exception {
        boolean flag = sysPermissionService.updateSysPermission(sysPermission);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统权限
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除SysPermission对象", notes = "删除系统权限", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysPermission(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysPermissionService.deleteSysPermission(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统权限
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取SysPermission对象详情", notes = "查看系统权限", response = SysPermissionQueryVo.class)
    public ApiResult<SysPermissionQueryVo> getSysPermission(@PathVariable("id") Long id) throws Exception {
        SysPermissionQueryVo sysPermissionQueryVo = sysPermissionService.getSysPermissionById(id);
        return ApiResult.ok(sysPermissionQueryVo);
    }

    /**
     * 系统权限分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysPermission分页列表", notes = "系统权限分页列表", response = SysPermissionQueryVo.class)
    public ApiResult<Paging<SysPermissionQueryVo>> getSysPermissionPageList(@Valid @RequestBody SysPermissionQueryParam sysPermissionQueryParam) throws Exception {
        Paging<SysPermissionQueryVo> paging = sysPermissionService.getSysPermissionPageList(sysPermissionQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取所有菜单列表
     */
    @PostMapping("/getAllMenuList")
    @ApiOperation(value = "获取所有菜单列表", notes = "获取所有菜单列表", response = SysPermissionTreeVo.class)
    public ApiResult<SysPermissionTreeVo> getAllMenuList() throws Exception {
        List<SysPermission> list = sysPermissionService.getAllMenuList();
        return ApiResult.ok(list);
    }

    /**
     * 获取获取菜单树形列表
     */
    @PostMapping("/getAllMenuTree")
    @ApiOperation(value = "获取所有菜单列表", notes = "获取所有菜单列表", response = SysPermissionTreeVo.class)
    public ApiResult<SysPermissionTreeVo> getAllMenuTree() throws Exception {
        List<SysPermissionTreeVo> list = sysPermissionService.getAllMenuTree();
        return ApiResult.ok(list);
    }

}

