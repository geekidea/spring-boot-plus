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

package io.geekidea.springbootplus.framework.util;

import io.geekidea.springbootplus.config.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件下载工具类
 *
 * @author geekidea
 * @date 2019/8/21
 * @since 1.2.1-RELEASE
 */
@Slf4j
public final class DownloadUtil {

    /**
     * 下载文件，使用默认下载处理器
     *
     * @param downloadDir
     * @param downloadFileName
     * @param allowFileExtensions
     * @param response
     * @throws Exception
     */
    public static void download(String downloadDir, String downloadFileName, List<String> allowFileExtensions, HttpServletResponse response) throws Exception {
        download(downloadDir, downloadFileName, allowFileExtensions, response, new DefaultDownloadHandler());
    }

    /**
     * 下载文件，使用自定义下载处理器
     *
     * @param downloadDir      文件目录
     * @param downloadFileName 文件名称
     * @throws Exception
     */
    public static void download(String downloadDir, String downloadFileName, List<String> allowFileExtensions, HttpServletResponse response, DownloadHandler downloadHandler) throws Exception {
        log.info("downloadDir:{}", downloadDir);
        log.info("downloadFileName:{}", downloadFileName);

        if (StringUtils.isBlank(downloadDir)) {
            throw new IOException("文件目录不能为空");
        }
        if (StringUtils.isBlank(downloadFileName)) {
            throw new IOException("文件名称不能为空");
        }
        // 安全判断，防止../情况,防止出现类似非法文件名称：../../hello/123.txt
        if (downloadFileName.contains(CommonConstant.SPOT_SPOT) || downloadFileName.contains(CommonConstant.SPOT_SPOT_BACKSLASH)) {
            throw new IOException("非法的文件名称");
        }
        // 允许下载的文件后缀判断
        if (CollectionUtils.isEmpty(allowFileExtensions)) {
            throw new IllegalArgumentException("请设置允许下载的文件后缀");
        }
        // 获取文件名称
        String fileExtension = FilenameUtils.getExtension(downloadFileName);

        // 从服务器读取文件，然后输出
        File downloadFile = new File(downloadDir, downloadFileName);
        if (!downloadFile.exists()) {
            throw new IOException("文件不存在");
        }

        // 判断文件类型，输出对应ContentType,如果没有对应的内容类型，可在config/mime-type.properties配置
        String contentType = ContentTypeUtil.getContentType(downloadFile);
        log.info("contentType:{}", contentType);
        // 文件大小
        long length = downloadFile.length();
        log.info("length:{}", length);

        // 下载回调处理
        if (downloadHandler == null) {
            // 使用默认下载处理器
            downloadHandler = new DefaultDownloadHandler();
        }
        boolean flag = downloadHandler.handle(downloadDir, downloadFileName, downloadFile, fileExtension, contentType, length);
        if (!flag) {
            log.info("下载自定义校验失败，取消下载");
            return;
        }

        // 下载文件名称编码，Firefox中文乱码处理
        String encodeDownFileName;

        HttpServletRequest request = HttpServletRequestUtil.getRequest();

        String browser = BrowserUtil.getCurrent(request);
        if (BrowserUtil.FIREFOX.equals(browser)) {
            encodeDownFileName = "=?UTF-8?B?" + (new String(Base64Utils.encodeToString(downloadFileName.getBytes("UTF-8")))) + "?=";
        } else {
            encodeDownFileName = URLEncoder.encode(downloadFileName, "utf-8").replaceAll("\\+", "%20");
        }
        log.info("encodeDownFileName:{}", encodeDownFileName);

        log.info("下载文件：" + downloadFile.getAbsolutePath());

        response.reset();
        // 设置Content-Disposition响应头
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + encodeDownFileName + "\"");
        // 设置响应Content-Type
        response.setContentType(contentType);
        // 设置响应文件大小
        response.setContentLengthLong(length);

        // 文件下载
        InputStream in = new BufferedInputStream(new FileInputStream(downloadFile));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    public static interface DownloadHandler {
        /**
         * 下载自定义处理
         *
         * @param dir
         * @param fileName
         * @param file
         * @param fileExtension
         * @param contentType
         * @param length
         * @return
         * @throws Exception
         */
        boolean handle(String dir, String fileName, File file, String fileExtension, String contentType, long length) throws Exception;
    }

    public static class DefaultDownloadHandler implements DownloadHandler {
        @Override
        public boolean handle(String dir, String fileName, File file, String fileExtension, String contentType, long length) throws Exception {
            return false;
        }
    }

}
