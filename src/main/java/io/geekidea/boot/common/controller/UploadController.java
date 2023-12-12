package io.geekidea.boot.common.controller;

import io.geekidea.boot.auth.annotation.Login;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.common.service.UploadService;
import io.geekidea.boot.common.vo.UploadVo;
import io.geekidea.boot.framework.response.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author geekidea
 * @date 2023/6/18
 **/
@Slf4j
@Login
@RestController
@RequestMapping("/common/upload")
@Tag(name = "文件上传")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 单个文件上传
     *
     * @param type
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "单个文件上传")
    @Permission("sys:file:upload")
    public ApiResult<UploadVo> upload(@RequestParam(required = false) String type,
                                      @RequestPart("file") MultipartFile multipartFile) throws Exception {
        UploadVo uploadVo = uploadService.upload(type, multipartFile);
        return ApiResult.success(uploadVo);
    }

    /**
     * 多个文件上传
     * 请使用swaggerUI测试多文件上传
     *
     * @param type
     * @param multipartFiles
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/batch", consumes = "multipart/form-data")
    @Operation(summary = "多个文件上传")
    @Permission("sys:file:upload-batch")
    public ApiResult<List<UploadVo>> uploadBatch(@RequestParam(required = false) String type,
                                                 @RequestPart("files") List<MultipartFile> multipartFiles) throws Exception {
        List<UploadVo> uploadVos = uploadService.uploadBatch(type, multipartFiles);
        return ApiResult.success(uploadVos);
    }

}
