package io.geekidea.boot.demo.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.query.DemoMerchantAppQuery;
import io.geekidea.boot.demo.service.DemoMerchantService;
import io.geekidea.boot.demo.vo.DemoMerchantAppVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App测试商户 控制器
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Slf4j
@RestController
@Tag(name = "App测试商户")
@RequestMapping("/app/demoMerchant")
public class DemoMerchantAppController {

    @Autowired
    private DemoMerchantService demoMerchantService;

    /**
     * 获取App测试商户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDemoMerchant/{id}")
    @Operation(summary = "App测试商户详情")
    public ApiResult<DemoMerchantAppVo> getAppDemoMerchant(@PathVariable Long id) throws Exception {
        DemoMerchantAppVo demoMerchantAppVo = demoMerchantService.getAppDemoMerchantById(id);
        return ApiResult.success(demoMerchantAppVo);
    }

    /**
     * App测试商户分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDemoMerchantPage")
    @Operation(summary = "App测试商户分页列表")
    public ApiResult<DemoMerchantAppVo> getAppDemoMerchantPage(@Valid @RequestBody DemoMerchantAppQuery query) throws Exception {
        Paging<DemoMerchantAppVo> paging = demoMerchantService.getAppDemoMerchantPage(query);
        return ApiResult.success(paging);
    }

}
