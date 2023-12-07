package io.geekidea.boot.demo.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.query.DemoAppQuery;
import io.geekidea.boot.demo.service.DemoService;
import io.geekidea.boot.demo.vo.DemoAppVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App演示 控制器
 *
 * @author geekidea
 * @since 2023-12-06
 */
@Slf4j
@RestController
@Tag(name = "App演示")
@RequestMapping("/app/demo")
public class DemoAppController {

    @Autowired
    private DemoService demoService;

    /**
     * 获取App演示详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDemo/{id}")
    @Operation(summary = "App演示详情")
    public ApiResult<DemoAppVo> getAppDemo(@PathVariable Long id) throws Exception {
        DemoAppVo demoAppVo = demoService.getAppDemoById(id);
        return ApiResult.success(demoAppVo);
    }

    /**
     * App演示分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDemoPage")
    @Operation(summary = "App演示分页列表")
    public ApiResult<DemoAppVo> getAppDemoPage(@Valid @RequestBody DemoAppQuery query) throws Exception {
        Paging<DemoAppVo> paging = demoService.getAppDemoPage(query);
        return ApiResult.success(paging);
    }

}
