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

package com.example.foorbar.controller;

import com.example.foorbar.entity.FooBar;
import com.example.foorbar.service.FooBarService;
import io.geekidea.springbootplus.framework.common.api.ApiCode;
import io.geekidea.springbootplus.framework.common.enums.BaseEnum;
import io.geekidea.springbootplus.framework.log.annotation.Module;
import io.geekidea.springbootplus.framework.log.annotation.OperationLog;
import io.geekidea.springbootplus.framework.log.enums.OperationLogType;
import io.geekidea.springbootplus.framework.system.enums.StateEnum;
import io.geekidea.springbootplus.framework.system.exception.SysLoginException;
import io.geekidea.springbootplus.framework.util.BaseEnumUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.geekidea.springbootplus.framework.common.controller.BaseController;
import com.example.foorbar.param.FooBarPageParam;
import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.common.param.IdParam;

/**
 * FooBar 控制器
 *
 * @author geekidea
 * @since 2020-03-17
 */
@Slf4j
@RestController
@RequestMapping("/fooBar")
@Module("foobar")
@Api("FooBarAPI")
public class FooBarController extends BaseController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 添加FooBar
     */
    @PostMapping("/add")
    @OperationLog(name = "添加FooBar", type = OperationLogType.ADD)
    @ApiOperation(value = "添加FooBar", response = ApiResult.class)
    public ApiResult<Boolean> addFooBar(@RequestBody FooBar fooBar) throws Exception {
        boolean flag = fooBarService.saveFooBar(fooBar);
        return ApiResult.ok(flag);
    }

    /**
     * 修改FooBar
     */
    @PostMapping("/update")
    @OperationLog(name = "修改FooBar", type = OperationLogType.UPDATE)
    @ApiOperation(value = "修改FooBar", response = ApiResult.class)
    public ApiResult<Boolean> updateFooBar(@RequestBody FooBar fooBar) throws Exception {
        boolean flag = fooBarService.updateFooBar(fooBar);
        return ApiResult.result(flag);
    }

    /**
     * 删除FooBar
     */
    @PostMapping("/delete/{id}")
    @OperationLog(name = "删除FooBar", type = OperationLogType.DELETE)
    @ApiOperation(value = "删除FooBar", response = ApiResult.class)
    public ApiResult<Boolean> deleteFooBar(@PathVariable("id") Long id) throws Exception {
        boolean flag = fooBarService.deleteFooBar(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取FooBar详情
     */
    @GetMapping("/info/{id}")
    @OperationLog(name = "查看FooBar", type = OperationLogType.INFO)
    @ApiOperation(value = "查看FooBar", response = FooBar.class)
    public ApiResult<FooBar> getFooBar(@PathVariable("id") Long id) throws Exception {
        FooBar fooBar = fooBarService.getById(id);
        return ApiResult.ok(fooBar);
    }

    /**
     * FooBar分页列表
     */
    @PostMapping("/getPageList")
    @OperationLog(name = "FooBar分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "FooBar分页列表", response = FooBar.class)
    public ApiResult<Paging<FooBar>> getFooBarPageList(@RequestBody FooBarPageParam fooBarPageParam) throws Exception {
        Paging<FooBar> paging = fooBarService.getFooBarPageList(fooBarPageParam);
        return ApiResult.ok(paging);
    }

}

