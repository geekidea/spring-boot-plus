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

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 获取文件的内容类型
 * mime-type参考：https://svn.apache.org/viewvc/httpd/httpd/trunk/docs/conf/mime.types?revision=1752884&view=co
 * @author geekidea
 * @date 2019/08/20
 * @since
 */
@Slf4j
public final class ContentTypeUtil {

    private static String MIME_TYPE_CONFIG_FILE = "config/mime-type.properties";

    private static String DEFAULT_MIME_TYPE = "application/octet-stream";

    private static Properties properties;

    static {
        try {
            properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(MIME_TYPE_CONFIG_FILE));
        } catch (IOException e) {
            log.error("读取配置文件" + MIME_TYPE_CONFIG_FILE + "异常",e);
        }
        log.info(MIME_TYPE_CONFIG_FILE + " = " + properties);
    }

    /**
     * 获取文件内容类型
     * @param file
     * @return
     */
    public static String getContentType(File file){
        if (file == null){
            return null;
        }
        Path path = Paths.get(file.toURI());
        if (path == null){
            return null;
        }
        String contentType = null;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            log.error("获取文件ContentType异常",e);
        }
        if (contentType == null){
            // 读取拓展的自定义配置
            contentType = getContentTypeByExtension(file);
        }
        // 设置默认的内容类型
        if (contentType == null){
            contentType = DEFAULT_MIME_TYPE;
        }
        return contentType;
    }

    /**
     * 根据文件后缀获取自定义配置的文件mime-type
     * @param file
     * @return
     */
    private static String getContentTypeByExtension(File file){
        if (properties == null){
            return null;
        }
        String extension = FilenameUtils.getExtension(file.getName());
        if (StringUtils.isBlank(extension)){
            return null;
        }
        String contentType = properties.getProperty(extension);
        return contentType;
    }

}
