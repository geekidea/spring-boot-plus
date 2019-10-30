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

package io.geekidea.springbootplus.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello World Controller
 * @author geekidea
 * @date 2019-10-09
 **/
@Slf4j
@Api("Hello World")
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @RequestMapping(value = "/world", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "Hello World", notes = "Hello World", response = String.class)
    public void print(HttpServletResponse response) throws IOException {
        log.debug("Hello World...");
        response.getWriter().print("Hello World");
    }

}
