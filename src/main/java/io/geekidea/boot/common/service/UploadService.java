package io.geekidea.boot.common.service;

import io.geekidea.boot.common.vo.UploadVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author geekidea
 * @date 2023/11/26
 **/
public interface UploadService {

    /**
     * 上传单个文件
     *
     * @param type
     * @param multipartFile
     * @return
     * @throws Exception
     */
    UploadVo upload(String type, MultipartFile multipartFile) throws Exception;

    /**
     * 批量上传文件
     *
     * @param type
     * @param multipartFiles
     * @return
     * @throws Exception
     */
    List<UploadVo> uploadBatch(String type, List<MultipartFile> multipartFiles) throws Exception;

}
