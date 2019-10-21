/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.example;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Shiro Example Controller
 *
 * @author geekidea
 * @date 2019-10-21
 **/
@Slf4j
@Api("Shiro Example")
@RestController
@RequestMapping("/shiroExample")
public class ShiroExampleController {

    @RequiresRoles("admin")
    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "Shiro Example", notes = "Shiro Example", response = String.class)
    public ApiResult print(HttpServletResponse response) throws IOException {
        log.debug("Shiro Example...");
        return ApiResult.ok("Shiro Example");
    }

}
