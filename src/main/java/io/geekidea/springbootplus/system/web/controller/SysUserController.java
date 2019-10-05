package io.geekidea.springbootplus.system.web.controller;

import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.system.service.SysUserService;
import io.geekidea.springbootplus.system.web.param.SysUserQueryParam;
import io.geekidea.springbootplus.system.web.vo.SysUserQueryVo;
import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.web.controller.BaseController;
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
 * SystemUser 前端控制器
 * </p>
 *
 * @author geekidea
 * @since 2019-10-05
 */
@Slf4j
@RestController
@RequestMapping("/SysUser")
@Api("SystemUser API")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService SysUserService;

    /**
     * 添加SystemUser
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysUser对象", notes = "添加SystemUser", response = ApiResult.class)
    public ApiResult<Boolean> addSysUser(@Valid @RequestBody SysUser SysUser) throws Exception {
        boolean flag = SysUserService.save(SysUser);
        return ApiResult.result(flag);
    }

    /**
     * 修改SystemUser
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysUser对象", notes = "修改SystemUser", response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Valid @RequestBody SysUser SysUser) throws Exception {
        boolean flag = SysUserService.updateById(SysUser);
        return ApiResult.result(flag);
    }

    /**
     * 删除SystemUser
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除SysUser对象", notes = "删除SystemUser", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysUser(@Valid @RequestBody IdParam idParam) throws Exception {
        boolean flag = SysUserService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
     * 获取SystemUser
     */
    @PostMapping("/info")
    @ApiOperation(value = "获取SysUser对象详情", notes = "查看SystemUser", response = SysUserQueryVo.class)
    public ApiResult<SysUserQueryVo> getSysUser(@Valid @RequestBody IdParam idParam) throws Exception {
        SysUserQueryVo SysUserQueryVo = SysUserService.getSysUserById(idParam.getId());
        return ApiResult.ok(SysUserQueryVo);
    }

    /**
     * SystemUser分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysUser分页列表", notes = "SystemUser分页列表", response = SysUserQueryVo.class)
    public ApiResult<Paging<SysUserQueryVo>> getSysUserPageList(@Valid @RequestBody(required = false) SysUserQueryParam SysUserQueryParam) throws Exception {
        Paging<SysUserQueryVo> paging = SysUserService.getSysUserPageList(SysUserQueryParam);
        return ApiResult.ok(paging);
    }

}

