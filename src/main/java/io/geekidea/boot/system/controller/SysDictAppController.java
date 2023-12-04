package io.geekidea.boot.system.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.query.SysDictAppQuery;
import io.geekidea.boot.system.service.SysDictService;
import io.geekidea.boot.system.vo.SysDictAppVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * App字典数据 控制器
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Slf4j
@RestController
@Tag(name = "App字典数据")
@RequestMapping("/app/sysDict")
public class SysDictAppController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * App字典数据列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppSysDictList")
    @Operation(summary = "App字典数据列表")
    public ApiResult<SysDictAppVo> getAppSysDictList(@Valid @RequestBody SysDictAppQuery query) throws Exception {
        Map<String,List<SysDictAppVo>> map = sysDictService.getAppSysDictList(query);
        return ApiResult.success(map);
    }

}
