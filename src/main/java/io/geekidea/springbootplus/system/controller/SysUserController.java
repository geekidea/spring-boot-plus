package io.geekidea.springbootplus.system.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.controller.BaseController;
import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.system.param.SysUserQueryParam;
import io.geekidea.springbootplus.system.service.SysUserService;
import io.geekidea.springbootplus.system.vo.SysUserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <pre>
 * 系统用户 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api("系统用户 API")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 添加系统用户
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysUser对象", notes = "添加系统用户", response = ApiResult.class)
    public ApiResult<Boolean> addSysUser(@Valid @RequestBody SysUser sysUser) throws Exception {
        boolean flag = sysUserService.saveSysUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统用户
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysUser对象", notes = "修改系统用户", response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Valid @RequestBody SysUser sysUser) throws Exception {
        boolean flag = sysUserService.updateSysUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统用户
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除SysUser对象", notes = "删除系统用户", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysUser(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysUserService.deleteSysUser(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统用户
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取SysUser对象详情", notes = "查看系统用户", response = SysUserQueryVo.class)
    public ApiResult<SysUserQueryVo> getSysUser(@PathVariable("id") Long id) throws Exception {
        SysUserQueryVo sysUserQueryVo = sysUserService.getSysUserById(id);
        return ApiResult.ok(sysUserQueryVo);
    }

    /**
     * 系统用户分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysUser分页列表", notes = "系统用户分页列表", response = SysUserQueryVo.class)
    public ApiResult<Paging<SysUserQueryVo>> getSysUserPageList(@Valid @RequestBody SysUserQueryParam sysUserQueryParam) throws Exception {
        Paging<SysUserQueryVo> paging = sysUserService.getSysUserPageList(sysUserQueryParam);
        return ApiResult.ok(paging);
    }

}

