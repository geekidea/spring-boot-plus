/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.system.controller;

import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.common.controller.BaseController;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.framework.properties.SpringBootPlusProperties;
import io.geekidea.springbootplus.framework.system.entity.SysUser;
import io.geekidea.springbootplus.framework.system.param.sysuser.ResetPasswordParam;
import io.geekidea.springbootplus.framework.system.param.sysuser.SysUserPageParam;
import io.geekidea.springbootplus.framework.system.param.sysuser.UpdatePasswordParam;
import io.geekidea.springbootplus.framework.system.param.sysuser.UploadHeadParam;
import io.geekidea.springbootplus.framework.system.service.SysUserService;
import io.geekidea.springbootplus.framework.system.vo.SysUserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "系统用户API", tags = {"系统用户"})
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SpringBootPlusProperties springBootPlusProperties;

    /**
     * 添加系统用户
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:user:add")
    @ApiOperation(value = "添加SysUser对象", notes = "添加系统用户", response = ApiResult.class)
    public ApiResult<Boolean> addSysUser(@Validated @RequestBody SysUser sysUser) throws Exception {
        boolean flag = sysUserService.saveSysUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统用户
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    @ApiOperation(value = "修改SysUser对象", notes = "修改系统用户", response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Validated @RequestBody SysUser sysUser) throws Exception {
        boolean flag = sysUserService.updateSysUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统用户
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:user:delete")
    @ApiOperation(value = "删除SysUser对象", notes = "删除系统用户", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysUser(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysUserService.deleteSysUser(id);
        return ApiResult.result(flag);
    }


    /**
     * 根据id获取系统用户
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:user:info:id")
    @ApiOperation(value = "获取SysUser对象详情", notes = "查看系统用户", response = SysUserQueryVo.class)
    public ApiResult<SysUserQueryVo> getSysUser(@PathVariable("id") Long id) throws Exception {
        SysUserQueryVo sysUserQueryVo = sysUserService.getSysUserById(id);
        return ApiResult.ok(sysUserQueryVo);
    }

    /**
     * 系统用户分页列表
     */
    @PostMapping("/getPageList")
//    @RequiresPermissions("sys:user:page")
    @ApiOperation(value = "获取SysUser分页列表", notes = "系统用户分页列表", response = SysUserQueryVo.class)
    public ApiResult<Paging<SysUserQueryVo>> getSysUserPageList(@Validated @RequestBody SysUserPageParam sysUserPageParam) throws Exception {
        Paging<SysUserQueryVo> paging = sysUserService.getSysUserPageList(sysUserPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    @RequiresPermissions("sys:user:update:password")
    @ApiOperation(value = "修改密码", response = ApiResult.class)
    public ApiResult<Boolean> updatePassword(@Validated @RequestBody UpdatePasswordParam updatePasswordParam) throws Exception {
        boolean flag = sysUserService.updatePassword(updatePasswordParam);
        return ApiResult.result(flag);
    }

    /**
     * 管理员重置用户密码
     */
    @PostMapping("/resetPassword")
//    @RequiresPermissions("sys:user:reset:password")
    @ApiOperation(value = "管理员重置用户密码", response = ApiResult.class)
    public ApiResult<Boolean> resetPassword(@Validated @RequestBody ResetPasswordParam resetPasswordParam) throws Exception {
        boolean flag = sysUserService.resetPassword(resetPasswordParam);
        return ApiResult.result(flag);
    }

    /**
     * 修改头像
     */
    @PostMapping("/uploadHead")
    @RequiresPermissions("sys:user:update:head")
    @ApiOperation(value = "修改头像", response = ApiResult.class)
    public ApiResult<Boolean> uploadHead(@Validated @RequestBody UploadHeadParam uploadHeadParam) throws Exception {
        boolean flag = sysUserService.updateSysUserHead(uploadHeadParam.getId(), uploadHeadParam.getHead());
        return ApiResult.result(flag);
    }
}

