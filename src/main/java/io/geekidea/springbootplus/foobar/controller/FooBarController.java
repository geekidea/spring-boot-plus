package io.geekidea.springbootplus.foobar.controller;

import io.geekidea.springbootplus.foobar.entity.FooBar;
import io.geekidea.springbootplus.foobar.service.FooBarService;
import io.geekidea.springbootplus.foobar.param.FooBarPageParam;
import io.geekidea.springbootplus.foobar.vo.FooBarQueryVo;
import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import io.geekidea.springbootplus.common.pagination.Paging;

/**
 * <pre>
 * FooBar 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-11-01
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
    public ApiResult<Boolean> addFooBar(@Valid @RequestBody FooBar fooBar) throws Exception {
        boolean flag = fooBarService.saveFooBar(fooBar);
        return ApiResult.result(flag);
    }

    /**
     * 修改FooBar
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改FooBar对象", notes = "修改FooBar", response = ApiResult.class)
    public ApiResult<Boolean> updateFooBar(@Valid @RequestBody FooBar fooBar) throws Exception {
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
    public ApiResult<Paging<FooBarQueryVo>> getFooBarPageList(@Valid @RequestBody FooBarPageParam fooBarPageParam) throws Exception {
        Paging<FooBarQueryVo> paging = fooBarService.getFooBarPageList(fooBarPageParam);
        return ApiResult.ok(paging);
    }

}

