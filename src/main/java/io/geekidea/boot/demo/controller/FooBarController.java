package io.geekidea.boot.demo.controller;

import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.dto.FooBarDto;
import io.geekidea.boot.demo.query.FooBarQuery;
import io.geekidea.boot.demo.service.FooBarService;
import io.geekidea.boot.demo.vo.FooBarVo;
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
 * @since 2023-12-02
 */
@Slf4j
@RestController
@Tag(name = "FooBar")
@RequestMapping("/admin/fooBar")
public class FooBarController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 添加FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加FooBar", type = SysLogType.ADD)
    @PostMapping("/addFooBar")
    @Operation(summary = "添加FooBar")
    @Permission("foo:bar:add")
    public ApiResult addFooBar(@Valid @RequestBody FooBarDto dto) throws Exception {
        boolean flag = fooBarService.addFooBar(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改FooBar", type = SysLogType.UPDATE)
    @PostMapping("/updateFooBar")
    @Operation(summary = "修改FooBar")
    @Permission("foo:bar:update")
    public ApiResult updateFooBar(@Valid @RequestBody FooBarDto dto) throws Exception {
        boolean flag = fooBarService.updateFooBar(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除FooBar
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除FooBar", type = SysLogType.DELETE)
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
    public ApiResult<FooBarVo> getFooBar(@PathVariable Long id) throws Exception {
        FooBarVo fooBarVo = fooBarService.getFooBarById(id);
        return ApiResult.success(fooBarVo);
    }

    /**
     * FooBar分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getFooBarPage")
    @Operation(summary = "FooBar分页列表")
    @Permission("foo:bar:page")
    public ApiResult<FooBarVo> getFooBarPage(@Valid @RequestBody FooBarQuery query) throws Exception {
        Paging<FooBarVo> paging = fooBarService.getFooBarPage(query);
        return ApiResult.success(paging);
    }

}
