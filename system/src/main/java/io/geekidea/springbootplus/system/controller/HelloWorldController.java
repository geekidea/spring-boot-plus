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

import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.log.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Hello World Controller
 *
 * @author geekidea
 * @date 2019-10-09
 **/
@Slf4j
@Api(value = "Hello World", tags = {"Hello World"})
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    /**
     * Hello World
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/world")
    @OperationLog(name = "helloWorld")
    @ApiOperation(value = "Hello World", response = String.class)
    public ApiResult<String> helloWorld() throws IOException {
        log.debug("Hello World...");
        return ApiResult.ok("Hello World");
    }

}
