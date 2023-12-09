package io.geekidea.boot.demo.controller;

import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.dto.DemoDto;
import io.geekidea.boot.demo.query.DemoQuery;
import io.geekidea.boot.demo.service.DemoService;
import io.geekidea.boot.demo.vo.DemoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 演示 控制器
 *
 * @author geekidea
 * @since 2023-12-09
 */
@Slf4j
@RestController
@Tag(name = "演示")
@RequestMapping("/admin/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * 添加演示
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加演示", type = SysLogType.ADD)
    @PostMapping("/addDemo")
    @Operation(summary = "添加演示")
    @Permission("demo:add")
    public ApiResult addDemo(@Valid @RequestBody DemoDto dto) throws Exception {
        boolean flag = demoService.addDemo(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改演示
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改演示", type = SysLogType.UPDATE)
    @PostMapping("/updateDemo")
    @Operation(summary = "修改演示")
    @Permission("demo:update")
    public ApiResult updateDemo(@Valid @RequestBody DemoDto dto) throws Exception {
        boolean flag = demoService.updateDemo(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除演示
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除演示", type = SysLogType.DELETE)
    @PostMapping("/deleteDemo/{id}")
    @Operation(summary = "删除演示")
    @Permission("demo:delete")
    public ApiResult deleteDemo(@PathVariable Long id) throws Exception {
        boolean flag = demoService.deleteDemo(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取演示详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getDemo/{id}")
    @Operation(summary = "演示详情")
    @Permission("demo:info")
    public ApiResult<DemoVo> getDemo(@PathVariable Long id) throws Exception {
        DemoVo demoVo = demoService.getDemoById(id);
        return ApiResult.success(demoVo);
    }

    /**
     * 演示分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getDemoPage")
    @Operation(summary = "演示分页列表")
    @Permission("demo:page")
    public ApiResult<DemoVo> getDemoPage(@Valid @RequestBody DemoQuery query) throws Exception {
        Paging<DemoVo> paging = demoService.getDemoPage(query);
        return ApiResult.success(paging);
    }

}
