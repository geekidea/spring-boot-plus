/**
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

package io.geekidea.springbootplus.upload.web;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.config.core.SpringBootPlusProperties;
import io.geekidea.springbootplus.util.DownloadUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 下载控制器
 * @author geekidea
 * @date 2019/8/20
 * @since 1.2.1-RELEASE
 */
@Slf4j
@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private SpringBootPlusProperties springBootPlusProperties;

    /**
     * 下载文件
     */
    @RequestMapping("/{downloadFileName}")
    @ApiOperation(value = "下载文件",notes = "下载文件",response = ApiResult.class)
    public void download(@PathVariable(required = true) String downloadFileName, HttpServletResponse response) throws Exception{
        DownloadUtil.download(springBootPlusProperties.getUploadPath(),downloadFileName,response);
    }

}
