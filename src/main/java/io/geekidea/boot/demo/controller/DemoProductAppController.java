package io.geekidea.boot.demo.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.query.DemoProductAppQuery;
import io.geekidea.boot.demo.service.DemoProductService;
import io.geekidea.boot.demo.vo.DemoProductAppVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App测试商品 控制器
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Slf4j
@RestController
@Tag(name = "App测试商品")
@RequestMapping("/app/demoProduct")
public class DemoProductAppController {

    @Autowired
    private DemoProductService demoProductService;

    /**
     * 获取App测试商品详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDemoProduct/{id}")
    @Operation(summary = "App测试商品详情")
    public ApiResult<DemoProductAppVo> getAppDemoProduct(@PathVariable Long id) throws Exception {
        DemoProductAppVo demoProductAppVo = demoProductService.getAppDemoProductById(id);
        return ApiResult.success(demoProductAppVo);
    }

    /**
     * App测试商品分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDemoProductPage")
    @Operation(summary = "App测试商品分页列表")
    public ApiResult<DemoProductAppVo> getAppDemoProductPage(@Valid @RequestBody DemoProductAppQuery query) throws Exception {
        Paging<DemoProductAppVo> paging = demoProductService.getAppDemoProductPage(query);
        return ApiResult.success(paging);
    }

}
