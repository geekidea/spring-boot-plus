package io.geekidea.boot.demo.controller;

import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.dto.DemoProductDto;
import io.geekidea.boot.demo.query.DemoProductQuery;
import io.geekidea.boot.demo.service.DemoProductService;
import io.geekidea.boot.demo.vo.DemoProductVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 测试商品 控制器
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Slf4j
@RestController
@Tag(name = "测试商品")
@RequestMapping("/admin/demoProduct")
public class DemoProductController {

    @Autowired
    private DemoProductService demoProductService;

    /**
     * 添加测试商品
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加测试商品", type = SysLogType.ADD)
    @PostMapping("/addDemoProduct")
    @Operation(summary = "添加测试商品")
    @Permission("demo:product:add")
    public ApiResult addDemoProduct(@Valid @RequestBody DemoProductDto dto) throws Exception {
        boolean flag = demoProductService.addDemoProduct(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改测试商品
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改测试商品", type = SysLogType.UPDATE)
    @PostMapping("/updateDemoProduct")
    @Operation(summary = "修改测试商品")
    @Permission("demo:product:update")
    public ApiResult updateDemoProduct(@Valid @RequestBody DemoProductDto dto) throws Exception {
        boolean flag = demoProductService.updateDemoProduct(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除测试商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除测试商品", type = SysLogType.DELETE)
    @PostMapping("/deleteDemoProduct/{id}")
    @Operation(summary = "删除测试商品")
    @Permission("demo:product:delete")
    public ApiResult deleteDemoProduct(@PathVariable Long id) throws Exception {
        boolean flag = demoProductService.deleteDemoProduct(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取测试商品详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getDemoProduct/{id}")
    @Operation(summary = "测试商品详情")
    @Permission("demo:product:info")
    public ApiResult<DemoProductVo> getDemoProduct(@PathVariable Long id) throws Exception {
        DemoProductVo demoProductVo = demoProductService.getDemoProductById(id);
        return ApiResult.success(demoProductVo);
    }

    /**
     * 测试商品分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getDemoProductPage")
    @Operation(summary = "测试商品分页列表")
    @Permission("demo:product:page")
    public ApiResult<DemoProductVo> getDemoProductPage(@Valid @RequestBody DemoProductQuery query) throws Exception {
        Paging<DemoProductVo> paging = demoProductService.getDemoProductPage(query);
        return ApiResult.success(paging);
    }

}
