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
import io.geekidea.springbootplus.common.pagination.Paging;
import io.geekidea.springbootplus.system.entity.SysDepartment;
import io.geekidea.springbootplus.system.param.SysDepartmentPageParam;
import io.geekidea.springbootplus.system.service.SysDepartmentService;
import io.geekidea.springbootplus.system.vo.SysDepartmentQueryVo;
import io.geekidea.springbootplus.system.vo.SysDepartmentTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <pre>
 * 部门 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/sysDepartment")
@Api("部门 API")
public class SysDepartmentController extends BaseController {

    @Autowired
    private SysDepartmentService sysDepartmentService;

    /**
     * 添加部门
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:department:add")
    @ApiOperation(value = "添加SysDepartment对象", notes = "添加部门", response = ApiResult.class)
    public ApiResult<Boolean> addSysDepartment(@Valid @RequestBody SysDepartment sysDepartment) throws Exception {
            boolean flag = sysDepartmentService.saveSysDepartment(sysDepartment);
            return ApiResult.result(flag);
    }

    /**
     * 修改部门
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:department:update")
    @ApiOperation(value = "修改SysDepartment对象", notes = "修改部门", response = ApiResult.class)
    public ApiResult<Boolean> updateSysDepartment(@Valid @RequestBody SysDepartment sysDepartment) throws Exception {
            boolean flag = sysDepartmentService.updateSysDepartment(sysDepartment);
            return ApiResult.result(flag);
    }

    /**
     * 删除部门
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:department:delete")
    @ApiOperation(value = "删除SysDepartment对象", notes = "删除部门", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysDepartment(@PathVariable("id") Long id) throws Exception {
            boolean flag = sysDepartmentService.deleteSysDepartment(id);
            return ApiResult.result(flag);
    }

    /**
     * 获取部门
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:department:info")
    @ApiOperation(value = "获取SysDepartment对象详情", notes = "查看部门", response = SysDepartmentQueryVo.class)
    public ApiResult<SysDepartmentQueryVo> getSysDepartment(@PathVariable("id") Long id) throws Exception {
        SysDepartmentQueryVo sysDepartmentQueryVo = sysDepartmentService.getSysDepartmentById(id);
        return ApiResult.ok(sysDepartmentQueryVo);
    }

    /**
     * 部门分页列表
     */
    @PostMapping("/getPageList")
    @RequiresPermissions("sys:department:page")
    @ApiOperation(value = "获取SysDepartment分页列表", notes = "部门分页列表", response = SysDepartmentQueryVo.class)
    public ApiResult<Paging<SysDepartmentQueryVo>> getSysDepartmentPageList(@Valid @RequestBody SysDepartmentPageParam sysDepartmentPageParam) throws Exception {
        Paging<SysDepartmentQueryVo> paging = sysDepartmentService.getSysDepartmentPageList(sysDepartmentPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取所有部门列表
     */
    @PostMapping("/getAllDepartmentList")
    @RequiresPermissions("sys:department:all:list")
    @ApiOperation(value = "获取所有部门的树形列表", notes = "获取所有部门的树形列表", response = SysDepartment.class)
    public ApiResult<Paging<SysDepartment>> getAllDepartmentList() throws Exception {
        List<SysDepartment> list = sysDepartmentService.getAllDepartmentList();
        return ApiResult.ok(list);
    }

    /**
     * 获取所有部门的树形列表
     */
    @PostMapping("/getAllDepartmentTree")
    @RequiresPermissions("sys:department:all:tree")
    @ApiOperation(value = "获取所有部门的树形列表", notes = "获取所有部门的树形列表", response = SysDepartmentTreeVo.class)
    public ApiResult<Paging<SysDepartmentTreeVo>> getAllDepartmentTree() throws Exception {
        List<SysDepartmentTreeVo> treeVos = sysDepartmentService.getAllDepartmentTree();
        return ApiResult.ok(treeVos);
    }

}

