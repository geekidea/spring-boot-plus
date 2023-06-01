package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysLoginLogAddDto;
import io.geekidea.boot.system.dto.SysLoginLogUpdateDto;
import io.geekidea.boot.system.query.SysLoginLogQuery;
import io.geekidea.boot.system.service.SysLoginLogService;
import io.geekidea.boot.system.vo.SysLoginLogInfoVo;
import io.geekidea.boot.system.vo.SysLoginLogVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统登录日志 控制器
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Slf4j
@RestController
@RequestMapping("/sysLoginLog")
@Tag(name = "系统登录日志")
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 添加系统登录日志
     *
     * @param sysLoginLogAddDto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysLoginLog")
    @Operation(summary = "添加系统登录日志")
    @Permission("sys:login:log:add")
    public ApiResult addSysLoginLog(@Valid @RequestBody SysLoginLogAddDto sysLoginLogAddDto) throws Exception {
        boolean flag = sysLoginLogService.addSysLoginLog(sysLoginLogAddDto);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统登录日志
     *
     * @param sysLoginLogUpdateDto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysLoginLog")
    @Operation(summary = "修改系统登录日志")
    @Permission("sys:login:log:update")
    public ApiResult updateSysLoginLog(@Valid @RequestBody SysLoginLogUpdateDto sysLoginLogUpdateDto) throws Exception {
        boolean flag = sysLoginLogService.updateSysLoginLog(sysLoginLogUpdateDto);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统登录日志
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysLoginLog/{id}")
    @Operation(summary = "删除系统登录日志")
    @Permission("sys:login:log:delete")
    public ApiResult deleteSysLoginLog(@PathVariable Long id) throws Exception {
        boolean flag = sysLoginLogService.deleteSysLoginLog(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统登录日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysLoginLog/{id}")
    @Operation(summary = "系统登录日志详情")
    @Permission("sys:login:log:info")
    public ApiResult<SysLoginLogInfoVo> getSysLoginLog(@PathVariable Long id) throws Exception {
        SysLoginLogInfoVo sysLoginLogInfoVo = sysLoginLogService.getSysLoginLogById(id);
        return ApiResult.success(sysLoginLogInfoVo);
    }

    /**
     * 系统登录日志分页列表
     *
     * @param sysLoginLogQuery
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysLoginLogList")
    @Operation(summary = "系统登录日志分页列表")
    @Permission("sys:login:log:list")
    public ApiResult<SysLoginLogVo> getSysLoginLogList(@Valid @RequestBody SysLoginLogQuery sysLoginLogQuery) throws Exception {
        Paging<SysLoginLogVo> paging = sysLoginLogService.getSysLoginLogList(sysLoginLogQuery);
        return ApiResult.success(paging);
    }

}
