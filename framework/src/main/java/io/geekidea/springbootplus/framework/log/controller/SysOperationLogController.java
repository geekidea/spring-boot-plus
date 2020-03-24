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

package io.geekidea.springbootplus.framework.log.controller;

import io.geekidea.springbootplus.framework.log.entity.SysOperationLog;
import io.geekidea.springbootplus.framework.log.service.SysOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.geekidea.springbootplus.framework.common.controller.BaseController;
import io.geekidea.springbootplus.framework.log.param.SysOperationLogPageParam;
import io.geekidea.springbootplus.framework.common.api.ApiResult;
import org.springframework.validation.annotation.Validated;
import io.geekidea.springbootplus.framework.core.validator.groups.Add;
import io.geekidea.springbootplus.framework.core.validator.groups.Update;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.common.param.IdParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 系统操作日志 控制器
 *
 * @author geekidea
 * @since 2020-03-19
 */
@Slf4j
@RestController
@RequestMapping("/sysOperationLog")
@Api(value = "系统操作日志API", tags = {"系统操作日志"})
public class SysOperationLogController extends BaseController {

    @Autowired
    private SysOperationLogService sysOperationLogService;

    /**
     * 添加系统操作日志
     */
    @PostMapping("/add")
    // @RequiresPermissions("sys:operation:log:add")
    @ApiOperation(value = "添加系统操作日志", response = ApiResult.class)
    public ApiResult<Boolean> addSysOperationLog(@Validated(Add.class) @RequestBody SysOperationLog sysOperationLog) throws Exception {
        boolean flag = sysOperationLogService.saveSysOperationLog(sysOperationLog);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统操作日志
     */
    @PostMapping("/update")
    // @RequiresPermissions("sys:operation:log:update")
    @ApiOperation(value = "修改系统操作日志", response = ApiResult.class)
    public ApiResult<Boolean> updateSysOperationLog(@Validated(Update.class) @RequestBody SysOperationLog sysOperationLog) throws Exception {
        boolean flag = sysOperationLogService.updateSysOperationLog(sysOperationLog);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统操作日志
     */
    @PostMapping("/delete/{id}")
    // @RequiresPermissions("sys:operation:log:delete")
    @ApiOperation(value = "删除系统操作日志", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysOperationLog(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysOperationLogService.deleteSysOperationLog(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统操作日志
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("sys:operation:log:info")
    @ApiOperation(value = "查看系统操作日志", response = SysOperationLog.class)
    public ApiResult<SysOperationLog> getSysOperationLog(@PathVariable("id") Long id) throws Exception {
        SysOperationLog sysOperationLog = sysOperationLogService.getById(id);
        return ApiResult.ok(sysOperationLog);
    }

    /**
     * 系统操作日志分页列表
     */
    @PostMapping("/getPageList")
    // @RequiresPermissions("sys:operation:log:page")
    @ApiOperation(value = "系统操作日志分页列表", response = SysOperationLog.class)
    public ApiResult<Paging<SysOperationLog>> getSysOperationLogPageList(@Validated @RequestBody SysOperationLogPageParam sysOperationLogPageParam) throws Exception {
        Paging<SysOperationLog> paging = sysOperationLogService.getSysOperationLogPageList(sysOperationLogPageParam);
        return ApiResult.ok(paging);
    }

}

