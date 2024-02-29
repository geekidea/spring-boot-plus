package io.geekidea.boot.system.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.system.dto.SysFileDto;
import io.geekidea.boot.system.query.SysFileQuery;
import io.geekidea.boot.system.service.SysFileService;
import io.geekidea.boot.system.vo.SysFileVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统文件 控制器
 *
 * @author geekidea
 * @since 2023-11-26
 */
@Slf4j
@RestController
@Tag(name = "系统文件")
@RequestMapping("/admin/sysFile")
public class SysFileController {

    @Autowired
    private SysFileService sysFileService;

    /**
     * 修改系统文件
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改系统文件", type = SysLogType.UPDATE)
    @PostMapping("/updateSysFile")
    @Operation(summary = "修改系统文件")
    @Permission("sys:file:update")
    public ApiResult updateSysFile(@Valid @RequestBody SysFileDto dto) {
        boolean flag = sysFileService.updateSysFile(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统文件
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除系统文件", type = SysLogType.DELETE)
    @PostMapping("/deleteSysFile/{id}")
    @Operation(summary = "删除系统文件")
    @Permission("sys:file:delete")
    public ApiResult deleteSysFile(@PathVariable Long id) {
        boolean flag = sysFileService.deleteSysFile(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统文件详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysFile/{id}")
    @Operation(summary = "系统文件详情")
    @Permission("sys:file:info")
    public ApiResult<SysFileVo> getSysFile(@PathVariable Long id) {
        SysFileVo sysFileVo = sysFileService.getSysFileById(id);
        return ApiResult.success(sysFileVo);
    }

    /**
     * 系统文件分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysFilePage")
    @Operation(summary = "系统文件分页列表")
    @Permission("sys:file:page")
    public ApiResult<SysFileVo> getSysFilePage(@Valid @RequestBody SysFileQuery query) {
        Paging<SysFileVo> paging = sysFileService.getSysFilePage(query);
        return ApiResult.success(paging);
    }

}
