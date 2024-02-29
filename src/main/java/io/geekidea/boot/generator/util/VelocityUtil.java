package io.geekidea.boot.generator.util;

import io.geekidea.boot.generator.constant.GeneratorConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @author geekidea
 * @date 2023/12/31
 **/
@Slf4j
public class VelocityUtil {

    private static Properties properties;

    static {
        properties = new Properties();
        properties.setProperty("resource.loader.file.class", ClasspathResourceLoader.class.getName());
        properties.setProperty("resource.loader.file.unicode", "true");
        properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
        properties.setProperty(Velocity.ENCODING_DEFAULT, GeneratorConstant.UTF8);
        properties.setProperty(Velocity.INPUT_ENCODING, GeneratorConstant.UTF8);

    }

    /**
     * 渲染模板数据输出到StringWrite中
     *
     * @param templatePath
     * @param dataMap
     * @return
     * @throws Exception
     */
    public static String writer(String templatePath, Map<String, Object> dataMap) {
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        StringWriter writer = new StringWriter();
        Template template = velocityEngine.getTemplate(templatePath, GeneratorConstant.UTF8);
        template.merge(new VelocityContext(dataMap), writer);
        String content = writer.toString();
        IOUtils.closeQuietly(writer);
        return content;
    }


}
