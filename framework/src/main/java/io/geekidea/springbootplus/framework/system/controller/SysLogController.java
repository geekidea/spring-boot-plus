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
import io.geekidea.springbootplus.framework.common.param.IdParam;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.system.entity.SysLog;
import io.geekidea.springbootplus.framework.system.param.SysLogPageParam;
import io.geekidea.springbootplus.framework.system.service.SysLogService;
import io.geekidea.springbootplus.framework.system.vo.SysLogQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
@Slf4j
@RestController
@RequestMapping("/sysLog")
@Api(value = "系统日志 API", tags = {"系统日志"})
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 添加系统日志
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysLog对象", notes = "添加系统日志", response = ApiResult.class)
    public ApiResult<Boolean> addSysLog(@Validated @RequestBody SysLog sysLog) throws Exception {
        boolean flag = sysLogService.save(sysLog);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统日志
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysLog对象", notes = "修改系统日志", response = ApiResult.class)
    public ApiResult<Boolean> updateSysLog(@Validated @RequestBody SysLog sysLog) throws Exception {
        boolean flag = sysLogService.updateById(sysLog);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统日志
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除SysLog对象", notes = "删除系统日志", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysLog(@Validated @RequestBody IdParam idParam) throws Exception {
        boolean flag = sysLogService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
     * 获取系统日志
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取SysLog对象详情", notes = "查看系统日志", response = SysLogQueryVo.class)
    public ApiResult<SysLogQueryVo> getSysLog(@Validated @RequestBody IdParam idParam) throws Exception {
        SysLogQueryVo sysLogQueryVo = sysLogService.getSysLogById(idParam.getId());
        return ApiResult.ok(sysLogQueryVo);
    }

    /**
     * 系统日志分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysLog分页列表", notes = "系统日志分页列表", response = SysLogQueryVo.class)
    public ApiResult<Paging<SysLogQueryVo>> getSysLogPageList(@Validated @RequestBody SysLogPageParam sysLogPageParam) throws Exception {
        Paging<SysLogQueryVo> paging = sysLogService.getSysLogPageList(sysLogPageParam);
        return ApiResult.ok(paging);
    }

}

