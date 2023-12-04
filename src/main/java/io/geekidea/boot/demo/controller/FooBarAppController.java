package io.geekidea.boot.demo.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.query.FooBarAppQuery;
import io.geekidea.boot.demo.service.FooBarService;
import io.geekidea.boot.demo.vo.FooBarAppVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * AppFooBar 控制器
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Slf4j
@RestController
@Tag(name = "AppFooBar")
@RequestMapping("/app/fooBar")
public class FooBarAppController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 获取AppFooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppFooBar/{id}")
    @Operation(summary = "AppFooBar详情")
    public ApiResult<FooBarAppVo> getAppFooBar(@PathVariable Long id) throws Exception {
        FooBarAppVo fooBarAppVo = fooBarService.getAppFooBarById(id);
        return ApiResult.success(fooBarAppVo);
    }

    /**
     * AppFooBar分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppFooBarPage")
    @Operation(summary = "AppFooBar分页列表")
    public ApiResult<FooBarAppVo> getAppFooBarPage(@Valid @RequestBody FooBarAppQuery query) throws Exception {
        Paging<FooBarAppVo> paging = fooBarService.getAppFooBarPage(query);
        return ApiResult.success(paging);
    }

}
