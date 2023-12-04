package io.geekidea.boot.demo.controller;

import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.dto.DataRangeAppTestDto;
import io.geekidea.boot.demo.query.DataRangeAppTestQuery;
import io.geekidea.boot.demo.service.DataRangeAppTestService;
import io.geekidea.boot.demo.vo.DataRangeAppTestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户端数据权限测试 控制器
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Slf4j
@RestController
@Tag(name = "用户端数据权限测试")
@RequestMapping("/admin/dataRangeAppTest")
public class DataRangeAppTestController {

    @Autowired
    private DataRangeAppTestService dataRangeAppTestService;

    /**
     * 添加用户端数据权限测试
     *
     * @param dataRangeAppTestDto
     * @return
     * @throws Exception
     */
    @Log(value = "添加用户端数据权限测试", type = SysLogType.ADD)
    @PostMapping("/addDataRangeAppTest")
    @Operation(summary = "添加用户端数据权限测试")
    @Permission("data:range:app:test:add")
    public ApiResult addDataRangeAppTest(@Valid @RequestBody DataRangeAppTestDto dataRangeAppTestDto) throws Exception {
        boolean flag = dataRangeAppTestService.addDataRangeAppTest(dataRangeAppTestDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改用户端数据权限测试
     *
     * @param dataRangeAppTestDto
     * @return
     * @throws Exception
     */
    @Log(value = "修改用户端数据权限测试", type = SysLogType.UPDATE)
    @PostMapping("/updateDataRangeAppTest")
    @Operation(summary = "修改用户端数据权限测试")
    @Permission("data:range:app:test:update")
    public ApiResult updateDataRangeAppTest(@Valid @RequestBody DataRangeAppTestDto dataRangeAppTestDto) throws Exception {
        boolean flag = dataRangeAppTestService.updateDataRangeAppTest(dataRangeAppTestDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除用户端数据权限测试
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除用户端数据权限测试", type = SysLogType.DELETE)
    @PostMapping("/deleteDataRangeAppTest/{id}")
    @Operation(summary = "删除用户端数据权限测试")
    @Permission("data:range:app:test:delete")
    public ApiResult deleteDataRangeAppTest(@PathVariable Long id) throws Exception {
        boolean flag = dataRangeAppTestService.deleteDataRangeAppTest(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取用户端数据权限测试详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getDataRangeAppTest/{id}")
    @Operation(summary = "用户端数据权限测试详情")
    @Permission("data:range:app:test:info")
    public ApiResult<DataRangeAppTestVo> getDataRangeAppTest(@PathVariable Long id) throws Exception {
        DataRangeAppTestVo dataRangeAppTestVo = dataRangeAppTestService.getDataRangeAppTestById(id);
        return ApiResult.success(dataRangeAppTestVo);
    }

    /**
     * 用户端数据权限测试分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getDataRangeAppTestPage")
    @Operation(summary = "用户端数据权限测试分页列表")
    @Permission("data:range:app:test:page")
    public ApiResult<DataRangeAppTestVo> getDataRangeAppTestPage(@Valid @RequestBody DataRangeAppTestQuery query) throws Exception {
        Paging<DataRangeAppTestVo> paging = dataRangeAppTestService.getDataRangeAppTestPage(query);
        return ApiResult.success(paging);
    }

}
