package io.geekidea.springbootplus.system.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.common.controller.BaseController;
import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.system.entity.SysRole;
import io.geekidea.springbootplus.system.param.SysRoleQueryParam;
import io.geekidea.springbootplus.system.service.SysRoleService;
import io.geekidea.springbootplus.system.vo.SysRoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <pre>
 * 系统角色 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")
@Api("系统角色 API")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加系统角色
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysRole对象", notes = "添加系统角色", response = ApiResult.class)
    public ApiResult<Boolean> addSysRole(@Valid @RequestBody SysRole sysRole) throws Exception {
            boolean flag = sysRoleService.saveSysRole(sysRole);
            return ApiResult.result(flag);
    }

    /**
     * 修改系统角色
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改SysRole对象", notes = "修改系统角色", response = ApiResult.class)
    public ApiResult<Boolean> updateSysRole(@Valid @RequestBody SysRole sysRole) throws Exception {
            boolean flag = sysRoleService.updateSysRole(sysRole);
            return ApiResult.result(flag);
    }

    /**
     * 删除系统角色
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除SysRole对象", notes = "删除系统角色", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysRole(@PathVariable("id") Long id) throws Exception {
            boolean flag = sysRoleService.deleteSysRole(id);
            return ApiResult.result(flag);
    }

    /**
     * 获取系统角色
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取SysRole对象详情", notes = "查看系统角色", response = SysRoleQueryVo.class)
    public ApiResult<SysRoleQueryVo> getSysRole(@PathVariable("id") Long id) throws Exception {
        SysRoleQueryVo sysRoleQueryVo = sysRoleService.getSysRoleById(id);
        return ApiResult.ok(sysRoleQueryVo);
    }

    /**
     * 系统角色分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysRole分页列表", notes = "系统角色分页列表", response = SysRoleQueryVo.class)
    public ApiResult<Paging<SysRoleQueryVo>> getSysRolePageList(@Valid @RequestBody SysRoleQueryParam sysRoleQueryParam) throws Exception {
        Paging<SysRoleQueryVo> paging = sysRoleService.getSysRolePageList(sysRoleQueryParam);
        return ApiResult.ok(paging);
    }

}

