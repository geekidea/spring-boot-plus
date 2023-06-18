package io.geekidea.boot.system.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.config.properties.FileProperties;
import io.geekidea.boot.framework.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * @author geekidea
 * @date 2023/6/18
 **/
@Slf4j
@RestController
@RequestMapping("/upload")
@Tag(name = "文件上传")
public class UploadController {

    @Autowired
    private FileProperties fileProperties;

    /**
     * 文件上传到本地
     *
     * @param multipartFile
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadLocal")
    @Operation(summary = "文件上传到本地")
    @Permission("sys:file:upload-local")
    public ApiResult uploadLocal(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("originalFilename:" + originalFilename);
        // 获取文件后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        String newFileName = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN) + RandomStringUtils.randomNumeric(6) + "." + extension;
        log.info("newFileName:" + newFileName);
        // 本地文件上传路径
        String uploadPath = fileProperties.getUploadPath();
        File uploadDir = new File(uploadPath);
        // 上传目录不存在，则直接创建
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // 上传文件到本地目录
        File uploadFile = new File(uploadDir, newFileName);
        log.info("uploadFile:" + uploadFile);
        multipartFile.transferTo(uploadFile);
        // 返回本地文件访问路径
        String accessFullPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + newFileName;
        log.info("accessFullPath:" + accessFullPath);
        return ApiResult.success(accessFullPath);
    }

}
