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
import java.util.List;

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
        // 下载目录，既是上传目录
        String downloadDir = springBootPlusProperties.getUploadPath();
        // 允许下载的文件后缀
        List<String> allowFileExtensions = springBootPlusProperties.getAllowDownloadFileExtensions();
        // 文件下载，使用默认下载处理器
//        DownloadUtil.download(downloadDir,downloadFileName,allowFileExtensions,response);
        // 文件下载，使用自定义下载处理器
        DownloadUtil.download(downloadDir,downloadFileName,allowFileExtensions,response, (dir, fileName, file, fileExtension, contentType, length) -> {
            // 下载自定义处理，返回true：执行下载，false：取消下载
            System.out.println("dir = " + dir);
            System.out.println("fileName = " + fileName);
            System.out.println("file = " + file);
            System.out.println("fileExtension = " + fileExtension);
            System.out.println("contentType = " + contentType);
            System.out.println("length = " + length);
            return true;
        });
    }

}
