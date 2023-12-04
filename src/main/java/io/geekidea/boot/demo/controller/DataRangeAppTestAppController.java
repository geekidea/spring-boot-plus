package io.geekidea.boot.demo.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.demo.query.DataRangeAppTestAppQuery;
import io.geekidea.boot.demo.service.DataRangeAppTestService;
import io.geekidea.boot.demo.vo.DataRangeAppTestAppVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App用户端数据权限测试 控制器
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Slf4j
@RestController
@Tag(name = "App用户端数据权限测试")
@RequestMapping("/app/dataRangeAppTest")
public class DataRangeAppTestAppController {

    @Autowired
    private DataRangeAppTestService dataRangeAppTestService;

    /**
     * 获取App用户端数据权限测试详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDataRangeAppTest/{id}")
    @Operation(summary = "App用户端数据权限测试详情")
    public ApiResult<DataRangeAppTestAppVo> getAppDataRangeAppTest(@PathVariable Long id) throws Exception {
        DataRangeAppTestAppVo dataRangeAppTestAppVo = dataRangeAppTestService.getAppDataRangeAppTestById(id);
        return ApiResult.success(dataRangeAppTestAppVo);
    }

    /**
     * App用户端数据权限测试分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppDataRangeAppTestPage")
    @Operation(summary = "App用户端数据权限测试分页列表")
    public ApiResult<DataRangeAppTestAppVo> getAppDataRangeAppTestPage(@Valid @RequestBody DataRangeAppTestAppQuery query) throws Exception {
        Paging<DataRangeAppTestAppVo> paging = dataRangeAppTestService.getAppDataRangeAppTestPage(query);
        return ApiResult.success(paging);
    }

}
