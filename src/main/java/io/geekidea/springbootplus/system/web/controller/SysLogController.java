package io.geekidea.springbootplus.system.web.controller;

import io.geekidea.springbootplus.system.entity.SysLog;
import io.geekidea.springbootplus.system.service.SysLogService;
import io.geekidea.springbootplus.system.web.param.SysLogQueryParam;
import io.geekidea.springbootplus.system.web.vo.SysLogQueryVo;
import io.geekidea.springbootplus.common.web.controller.BaseController;
import io.geekidea.springbootplus.common.api.ApiResult;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.geekidea.springbootplus.common.web.vo.Paging;
import io.geekidea.springbootplus.common.web.param.IdParam;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author geekidea
 * @since 2019-07-27
 */
@RestController
@RequestMapping("/sysLog")
@Slf4j
@Api("系统日志 API")
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    /**
    * 添加系统日志
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysLog对象",notes = "添加系统日志",response = ApiResult.class)
    public ApiResult<Boolean> addSysUser(@Valid @RequestBody SysLog sysLog) throws Exception{
        boolean flag = sysLogService.save(sysLog);
        return ApiResult.result(flag);
    }

    /**
    * 修改系统日志
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysLog对象",notes = "修改系统日志",response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Valid @RequestBody SysLog sysLog) throws Exception{
        boolean flag = sysLogService.updateById(sysLog);
        return ApiResult.result(flag);
    }

    /**
    * 删除系统日志
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除SysLog对象",notes = "删除系统日志",response = ApiResult.class)
    public ApiResult<Boolean> deleteSysUser(@Valid @RequestBody IdParam idParam) throws Exception{
        boolean flag = sysLogService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
    * 获取系统日志
    */
    @PostMapping("/info")
    @ApiOperation(value = "获取SysLog对象详情",notes = "查看系统日志",response = SysLogQueryVo.class)
    public ApiResult<SysLogQueryVo> getSysUser(@Valid @RequestBody IdParam idParam) throws Exception{
        SysLogQueryVo sysLogQueryVo = sysLogService.getSysLogById(idParam.getId());
        return ApiResult.ok(sysLogQueryVo);
    }

    /**
     * 系统日志分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysLog分页列表",notes = "系统日志分页列表",response = SysLogQueryVo.class)
    public ApiResult<Paging<SysLogQueryVo>> getSysLogPageList(@Valid @RequestBody(required = false) SysLogQueryParam sysLogQueryParam) throws Exception{
        Paging<SysLogQueryVo> paging = sysLogService.getSysLogPageList(sysLogQueryParam);
        return ApiResult.ok(paging);
    }

}

