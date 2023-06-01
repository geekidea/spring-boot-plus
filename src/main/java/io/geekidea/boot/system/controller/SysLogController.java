package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysLogAddDto;
import io.geekidea.boot.system.dto.SysLogUpdateDto;
import io.geekidea.boot.system.query.SysLogQuery;
import io.geekidea.boot.system.service.SysLogService;
import io.geekidea.boot.system.vo.SysLogInfoVo;
import io.geekidea.boot.system.vo.SysLogVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统日志 控制器
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Slf4j
@RestController
@RequestMapping("/sysLog")
@Tag(name = "系统日志")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 添加系统日志
     *
     * @param sysLogAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysLog")
    @Operation(summary = "添加系统日志")
    @Permission("sys:log:add")
    public ApiResult addSysLog(@Valid @RequestBody SysLogAddDto sysLogAddDto) throws Exception {
        boolean flag = sysLogService.addSysLog(sysLogAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统日志
     *
     * @param sysLogUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysLog")
    @Operation(summary = "修改系统日志")
    @Permission("sys:log:update")
    public ApiResult updateSysLog(@Valid @RequestBody SysLogUpdateDto sysLogUpdateDto) throws Exception {
        boolean flag = sysLogService.updateSysLog(sysLogUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统日志
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysLog/{id}")
    @Operation(summary = "删除系统日志")
    @Permission("sys:log:delete")
    public ApiResult deleteSysLog(@PathVariable Long id) throws Exception {
        boolean flag = sysLogService.deleteSysLog(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysLog/{id}")
    @Operation(summary = "系统日志详情")
    @Permission("sys:log:info")
    public ApiResult<SysLogInfoVo> getSysLog(@PathVariable Long id) throws Exception {
        SysLogInfoVo sysLogInfoVo = sysLogService.getSysLogById(id);
        return ApiResult.success(sysLogInfoVo);
    }

    /**
     * 系统日志分页列表
     *
     * @param sysLogQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysLogList")
    @Operation(summary = "系统日志分页列表")
    @Permission("sys:log:list")
    public ApiResult<SysLogVo> getSysLogList(@Valid @RequestBody SysLogQuery sysLogQuery) throws Exception {
        Paging<SysLogVo> paging = sysLogService.getSysLogList(sysLogQuery);
        return ApiResult.success(paging);
    }

}
