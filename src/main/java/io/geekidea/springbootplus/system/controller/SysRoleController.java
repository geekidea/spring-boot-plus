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

import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.common.controller.BaseController;
import io.geekidea.springbootplus.framework.pagination.Paging;
import io.geekidea.springbootplus.framework.validator.groups.Add;
import io.geekidea.springbootplus.framework.validator.groups.Update;
import io.geekidea.springbootplus.system.entity.SysRole;
import io.geekidea.springbootplus.system.param.sysrole.SysRolePageParam;
import io.geekidea.springbootplus.system.param.sysrole.UpdateSysRolePermissionParam;
import io.geekidea.springbootplus.system.service.SysRoleService;
import io.geekidea.springbootplus.system.vo.SysRoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <pre>
 * 系统角色 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")
@Api("系统角色 API")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加系统角色
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:role:add")
    @ApiOperation(value = "添加SysRole对象", notes = "添加系统角色", response = ApiResult.class)
    public ApiResult<Boolean> addSysRole(@Validated(Add.class) @RequestBody SysRole sysRole) throws Exception {
            boolean flag = sysRoleService.saveSysRole(sysRole);
            return ApiResult.result(flag);
    }

    /**
     * 修改系统角色
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    @ApiOperation(value = "修改SysRole对象", notes = "修改系统角色", response = ApiResult.class)
    public ApiResult<Boolean> updateSysRole(@Validated(Update.class) @RequestBody SysRole sysRole) throws Exception {
            boolean flag = sysRoleService.updateSysRole(sysRole);
            return ApiResult.result(flag);
    }

    /**
     * 删除系统角色
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:role:delete")
    @ApiOperation(value = "删除SysRole对象", notes = "删除系统角色", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysRole(@PathVariable("id") Long id) throws Exception {
            boolean flag = sysRoleService.deleteSysRole(id);
            return ApiResult.result(flag);
    }

    /**
     * 获取系统角色
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:role:info")
    @ApiOperation(value = "获取SysRole对象详情", notes = "查看系统角色", response = SysRoleQueryVo.class)
    public ApiResult<SysRoleQueryVo> getSysRole(@PathVariable("id") Long id) throws Exception {
        SysRoleQueryVo sysRoleQueryVo = sysRoleService.getSysRoleById(id);
        return ApiResult.ok(sysRoleQueryVo);
    }

    /**
     * 系统角色分页列表
     */
    @PostMapping("/getPageList")
    @RequiresPermissions("sys:role:page")
    @ApiOperation(value = "获取SysRole分页列表", notes = "系统角色分页列表", response = SysRoleQueryVo.class)
    public ApiResult<Paging<SysRoleQueryVo>> getSysRolePageList(@Validated @RequestBody SysRolePageParam sysRolePageParam) throws Exception {
        Paging<SysRoleQueryVo> paging = sysRoleService.getSysRolePageList(sysRolePageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取系统角色列表
     * @return
     */
    @PostMapping("/getList")
    @RequiresPermissions("sys:role:list")
    @ApiOperation(value = "获取SysRole列表", notes = "系统角色列表", response = SysRole.class)
    public ApiResult<List<SysRole>> getRoleList() {
        return ApiResult.ok(sysRoleService.list());
    }

    /**
     * 修改系统角色权限
     */
    @PostMapping("/updateSysRolePermission")
//    @RequiresPermissions("sys:role-permission:update")
    @ApiOperation(value = "修改系统角色权限", response = ApiResult.class)
    public ApiResult<Boolean> updateSysRolePermission(@Validated @RequestBody UpdateSysRolePermissionParam param) throws Exception {
        boolean flag = sysRoleService.updateSysRolePermission(param);
        return ApiResult.result(flag);
    }

}

