package io.geekidea.boot.demo.controller;

import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.dto.DemoMerchantDto;
import io.geekidea.boot.demo.query.DemoMerchantQuery;
import io.geekidea.boot.demo.service.DemoMerchantService;
import io.geekidea.boot.demo.vo.DemoMerchantVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 测试商户 控制器
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Slf4j
@RestController
@Tag(name = "测试商户")
@RequestMapping("/admin/demoMerchant")
public class DemoMerchantController {

    @Autowired
    private DemoMerchantService demoMerchantService;

    /**
     * 添加测试商户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加测试商户", type = SysLogType.ADD)
    @PostMapping("/addDemoMerchant")
    @Operation(summary = "添加测试商户")
    @Permission("demo:merchant:add")
    public ApiResult addDemoMerchant(@Valid @RequestBody DemoMerchantDto dto) throws Exception {
        boolean flag = demoMerchantService.addDemoMerchant(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改测试商户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改测试商户", type = SysLogType.UPDATE)
    @PostMapping("/updateDemoMerchant")
    @Operation(summary = "修改测试商户")
    @Permission("demo:merchant:update")
    public ApiResult updateDemoMerchant(@Valid @RequestBody DemoMerchantDto dto) throws Exception {
        boolean flag = demoMerchantService.updateDemoMerchant(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除测试商户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除测试商户", type = SysLogType.DELETE)
    @PostMapping("/deleteDemoMerchant/{id}")
    @Operation(summary = "删除测试商户")
    @Permission("demo:merchant:delete")
    public ApiResult deleteDemoMerchant(@PathVariable Long id) throws Exception {
        boolean flag = demoMerchantService.deleteDemoMerchant(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取测试商户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getDemoMerchant/{id}")
    @Operation(summary = "测试商户详情")
    @Permission("demo:merchant:info")
    public ApiResult<DemoMerchantVo> getDemoMerchant(@PathVariable Long id) throws Exception {
        DemoMerchantVo demoMerchantVo = demoMerchantService.getDemoMerchantById(id);
        return ApiResult.success(demoMerchantVo);
    }

    /**
     * 测试商户分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getDemoMerchantPage")
    @Operation(summary = "测试商户分页列表")
    @Permission("demo:merchant:page")
    public ApiResult<DemoMerchantVo> getDemoMerchantPage(@Valid @RequestBody DemoMerchantQuery query) throws Exception {
        Paging<DemoMerchantVo> paging = demoMerchantService.getDemoMerchantPage(query);
        return ApiResult.success(paging);
    }

}
