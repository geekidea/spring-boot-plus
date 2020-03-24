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

package io.geekidea.springbootplus.framework.common.controller;

import io.geekidea.springbootplus.framework.log.annotation.OperationLogIgnore;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 项目根路径提示信息
 * </p>
 *
 * @author geekidea
 * @date 2018/11/12
 */
@Slf4j
@Controller
@OperationLogIgnore
@Api(value = "Index API", tags = {"Index"})
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    /**
     * SwaggerUI
     */
    @GetMapping("/docs")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }

}
