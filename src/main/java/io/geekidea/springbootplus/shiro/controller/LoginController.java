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

package io.geekidea.springbootplus.shiro.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.shiro.jwt.JwtProperties;
import io.geekidea.springbootplus.shiro.jwt.JwtToken;
import io.geekidea.springbootplus.shiro.cache.LoginRedisService;
import io.geekidea.springbootplus.shiro.param.LoginParam;
import io.geekidea.springbootplus.shiro.service.LoginService;
import io.geekidea.springbootplus.system.web.vo.SysUserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登陆控制器
 *
 * @author geekidea
 * @date 2019-09-28
 * @since 1.3.0.RELEASE
 **/
@Api("登陆控制器")
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginRedisService loginRedisService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "系统用户登陆", response = SysUserQueryVo.class)
    public ApiResult login(@Valid @RequestBody LoginParam loginParam, HttpServletResponse response) {
        return loginService.login(loginParam, response);
    }

    @PostMapping("/logout")
    public ApiResult logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        // 删除Redis缓存信息
        JwtToken jwtToken = (JwtToken) subject.getPrincipal();
        System.out.println("jwtToken = " + jwtToken);
        loginRedisService.deleteLoginInfo(jwtToken);
        return ApiResult.ok("退出成功");
    }
}
