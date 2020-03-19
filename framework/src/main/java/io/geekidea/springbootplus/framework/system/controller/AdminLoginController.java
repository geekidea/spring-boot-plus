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
import io.geekidea.springbootplus.framework.core.constant.CommonConstant;
import io.geekidea.springbootplus.framework.system.param.AdminLoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于SpringBootAdmin登陆控制
 *
 * @author geekidea
 * @date 2020/3/15
 **/
@Slf4j
@Controller
@RequestMapping("/admin")
@Api(value = "AdminLogin", tags = {"SpringBootAdmin登陆"})
public class AdminLoginController {

    @GetMapping(value = {"/login", "/login.html"})
    public ModelAndView adminLogin(ModelAndView modelAndView) {
        log.debug("adminLogin page....");
        return new ModelAndView("redirect:/static/login.html");
    }

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "SpringBootAdmin登陆", response = ApiResult.class)
    public ApiResult adminLogin(@Validated @RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        log.info("admin login");
        if ("admin".equals(adminLoginParam.getUsername()) && "admin".equals(adminLoginParam.getPassword())) {
            log.info("adminLogin成功");
            request.getSession().setAttribute(CommonConstant.ADMIN_LOGIN_SESSION, true);
            return ApiResult.ok();
        }
        return ApiResult.fail();
    }


    @GetMapping("/logout")
    @ResponseBody
    @ApiOperation(value = "SpringBootAdmin登出", response = ApiResult.class)
    public ModelAndView adminLogout(ModelAndView modelAndView, HttpServletRequest request) {
        log.info("adminLogout");
        request.getSession().setAttribute(CommonConstant.ADMIN_LOGIN_SESSION, false);
        modelAndView.setViewName("redirect:/static/login.html");
        return modelAndView;
    }

}
