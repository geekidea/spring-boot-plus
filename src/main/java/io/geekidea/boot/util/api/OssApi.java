package io.geekidea.boot.util.api;

import com.alibaba.fastjson2.JSON;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import io.geekidea.boot.config.properties.OssProperties;
import io.geekidea.boot.framework.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.InputStream;

/**
 * OSS接口调用工具类
 * @author geekidea
 * @date 2023/11/26
 **/
@Slf4j
@Component
@ConditionalOnProperty(name = "file.file-server-type", havingValue = "OSS", matchIfMissing = true)
public class OssApi {

    private static OssProperties ossProperties;

    private static OSS ossClient;

    public OssApi(OssProperties ossProperties) {
        OssApi.ossProperties = ossProperties;
    }

    @PostConstruct
    public void initOssClient() {
        try {
            DefaultCredentialProvider credentialsProvider = CredentialsProviderFactory.newDefaultCredentialProvider(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
            // 创建OSSClient实例
            ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), credentialsProvider);
            log.info("OSS实例初始化成功：" + JSON.toJSONString(ossProperties));
        } catch (Exception e) {
            log.error("OSS实例初始化异常：" + JSON.toJSONString(ossProperties));
            e.printStackTrace();
        }
    }

    public static String upload(InputStream inputStream, String dirName, String fileName) throws Exception {
        try {
            String fileKey = dirName + "/" + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossProperties.getBucketName(), fileKey, inputStream);
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            log.info("OSS上传文件结果：" + JSON.toJSONString(result));
            String accessUrl = ossProperties.getAccessDomain();
            String url = accessUrl + "/" + fileKey;
            // 返回访问路径
            log.info("OSS上传文件成功，fileKey：{}，url：{}", fileKey, url);
            return url;
        } catch (OSSException oe) {
            log.error("OSS上传文件异常，错误消息：{}，错误码：{}，请求ID：{}，主机ID：{}", oe.getErrorMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            throw new BusinessException("OSS上传文件异常");
        } catch (ClientException ce) {
            log.error("OSS客户端异常：" + ce.getMessage());
            throw new BusinessException("OSS连接异常");
        } catch (Exception e) {
            throw e;
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
