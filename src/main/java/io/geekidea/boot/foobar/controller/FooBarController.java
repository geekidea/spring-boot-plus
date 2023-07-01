package io.geekidea.boot.foobar.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.foobar.dto.FooBarAddDto;
import io.geekidea.boot.foobar.dto.FooBarUpdateDto;
import io.geekidea.boot.foobar.query.FooBarQuery;
import io.geekidea.boot.foobar.service.FooBarService;
import io.geekidea.boot.foobar.vo.FooBarInfoVo;
import io.geekidea.boot.foobar.vo.FooBarVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * FooBar 控制器
 *
 * @author geekidea
 * @since 2023-07-01
 */
@Slf4j
@RestController
@RequestMapping("/fooBar")
@Tag(name = "FooBar")
public class FooBarController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 添加FooBar
     *
     * @param fooBarAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addFooBar")
    @Operation(summary = "添加FooBar")
    @Permission("foo:bar:add")
    public ApiResult addFooBar(@Valid @RequestBody FooBarAddDto fooBarAddDto) throws Exception {
        boolean flag = fooBarService.addFooBar(fooBarAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改FooBar
     *
     * @param fooBarUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateFooBar")
    @Operation(summary = "修改FooBar")
    @Permission("foo:bar:update")
    public ApiResult updateFooBar(@Valid @RequestBody FooBarUpdateDto fooBarUpdateDto) throws Exception {
        boolean flag = fooBarService.updateFooBar(fooBarUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除FooBar
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteFooBar/{id}")
    @Operation(summary = "删除FooBar")
    @Permission("foo:bar:delete")
    public ApiResult deleteFooBar(@PathVariable Long id) throws Exception {
        boolean flag = fooBarService.deleteFooBar(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取FooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getFooBar/{id}")
    @Operation(summary = "FooBar详情")
    @Permission("foo:bar:info")
    public ApiResult<FooBarInfoVo> getFooBar(@PathVariable Long id) throws Exception {
        FooBarInfoVo fooBarInfoVo = fooBarService.getFooBarById(id);
        return ApiResult.success(fooBarInfoVo);
    }

    /**
     * FooBar分页列表
     *
     * @param fooBarQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getFooBarList")
    @Operation(summary = "FooBar分页列表")
    @Permission("foo:bar:list")
    public ApiResult<FooBarVo> getFooBarList(@Valid @RequestBody FooBarQuery fooBarQuery) throws Exception {
        Paging<FooBarVo> paging = fooBarService.getFooBarList(fooBarQuery);
        return ApiResult.success(paging);
    }

}
