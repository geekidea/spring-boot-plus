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

package com.example.foobar.controller;

import com.example.foobar.entity.FooBar;
import com.example.foobar.service.FooBarService;
import io.geekidea.springbootplus.framework.pagination.Paging;
import com.example.foobar.param.FooBarPageParam;
import com.example.foobar.vo.FooBarQueryVo;
import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.common.controller.BaseController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import io.geekidea.springbootplus.framework.common.param.IdParam;

/**
 * <pre>
 * FooBar 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2020-03-23
 */
@Slf4j
@RestController
@RequestMapping("/fooBar")
@Api("FooBar API")
public class FooBarController extends BaseController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 添加FooBar
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加FooBar对象", notes = "添加FooBar", response = ApiResult.class)
    public ApiResult<Boolean> addFooBar(@Validated @RequestBody FooBar fooBar) throws Exception {
        boolean flag = fooBarService.saveFooBar(fooBar);
        return ApiResult.result(flag);
    }

    /**
     * 修改FooBar
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改FooBar对象", notes = "修改FooBar", response = ApiResult.class)
    public ApiResult<Boolean> updateFooBar(@Validated @RequestBody FooBar fooBar) throws Exception {
        boolean flag = fooBarService.updateFooBar(fooBar);
        return ApiResult.result(flag);
    }

    /**
     * 删除FooBar
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除FooBar对象", notes = "删除FooBar", response = ApiResult.class)
    public ApiResult<Boolean> deleteFooBar(@PathVariable("id") Long id) throws Exception {
        boolean flag = fooBarService.deleteFooBar(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取FooBar
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取FooBar对象详情", notes = "查看FooBar", response = FooBarQueryVo.class)
    public ApiResult<FooBarQueryVo> getFooBar(@PathVariable("id") Long id) throws Exception {
        FooBarQueryVo fooBarQueryVo = fooBarService.getFooBarById(id);
        return ApiResult.ok(fooBarQueryVo);
    }

    /**
     * FooBar分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取FooBar分页列表", notes = "FooBar分页列表", response = FooBarQueryVo.class)
    public ApiResult<Paging<FooBarQueryVo>> getFooBarPageList(@Validated @RequestBody FooBarPageParam fooBarPageParam) throws Exception {
        Paging<FooBarQueryVo> paging = fooBarService.getFooBarPageList(fooBarPageParam);
        return ApiResult.ok(paging);
    }

}

