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

import io.geekidea.springbootplus.config.properties.SpringBootPlusProperties;
import io.geekidea.springbootplus.framework.common.api.ApiResult;
import io.geekidea.springbootplus.framework.log.annotation.Module;
import io.geekidea.springbootplus.framework.log.annotation.OperationLog;
import io.geekidea.springbootplus.framework.log.enums.OperationLogType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 图片等文件资源访问控制器
 * /api/resource 访问路径 用于区分 文件访问虚拟目录映射 /resource
 *
 * @author geekidea
 * @date 2019/8/20
 * @since 1.2.1-RELEASE
 */
@Slf4j
@Controller
@RequestMapping("/api/resource")
@Module("system")
@Api(value = "资源访问", tags = {"资源访问"})
public class ResourceController {

    @Autowired
    private SpringBootPlusProperties springBootPlusProperties;

    /**
     * 访问资源
     */
    @GetMapping("/image/{imageFileName}")
    @OperationLog(name = "访问资源", type = OperationLogType.ADD)
    @ApiOperation(value = "访问资源", response = ApiResult.class)
    public void getImage(@PathVariable(required = true) String imageFileName, HttpServletResponse response) throws Exception {
        log.info("imageFileName:{}", imageFileName);
        // 重定向到图片访问路径
        response.sendRedirect(springBootPlusProperties.getResourceAccessPath() + imageFileName);
    }

}
