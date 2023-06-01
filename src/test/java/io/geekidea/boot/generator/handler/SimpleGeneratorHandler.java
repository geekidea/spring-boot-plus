package io.geekidea.boot.generator.handler;

import cn.hutool.core.date.DateUtil;
import io.geekidea.boot.framework.constant.CommonConstant;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.generator.config.SimpleGeneratorConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author geekidea
 * @date 2022/7/2
 **/
@Slf4j
public class SimpleGeneratorHandler {
    private static final String PACKAGE_NAME = CommonConstant.PACKAGE_NAME;
    private static final String CONTROLLER_PACKAGE_NAME = ".controller";
    private static final String SERVICE_PACKAGE_NAME = ".service";
    private static final String SERVICE_IMPL_PACKAGE_NAME = ".service.impl";
    private static final String MAPPER_PACKAGE_NAME = ".mapper";
    private static final String CONTROLLER_SUFFIX = "Controller";
    private static final String SERVICE_SUFFIX = "Service";
    private static final String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    private static final String MAPPER_SUFFIX = "Mapper";
    private static final String TEMPLATE_DIR = "templates" + File.separator + "simple";
    private static final String CONTROLLER_TEMPLATE_NAME = "simpleController.java.vm";
    private static final String SERVICE_TEMPLATE_NAME = "simpleService.java.vm";
    private static final String SERVICE_IMPL_TEMPLATE_NAME = "simpleServiceImpl.java.vm";
    private static final String MAPPER_TEMPLATE_NAME = "simpleMapper.java.vm";
    private static final String MAPPER_XML_TEMPLATE_NAME = "simpleMapper.xml.vm";
    private static final String DOT_JAVA = ".java";
    private static final String DOT_XML = ".xml";
    private static final String USER_DIR = "user.dir";
    private static final String JAVA_DIR = File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
    private static final String RESOURCES_DIR = File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    private static final String TEST_RESOURCES_DIR = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String MAPPER_XML_DIR = RESOURCES_DIR + File.separator + "mapper" + File.separator;

    public static void generateSimple(SimpleGeneratorConfig config) throws Exception {
        if (config == null) {
            throw new BusinessException("配置不能为空");
        }
        boolean generateController = config.isGenerateController();
        boolean generateService = config.isGenerateService();
        boolean generateMapper = config.isGenerateMapper();
        if (!generateController && !generateService && !generateMapper) {
            log.warn("未设置需要要生成的内容");
            return;
        }
        String name = config.getName();
        String desc = config.getDesc();
        String author = config.getAuthor();
        String moduleName = config.getModuleName();
        if (StringUtils.isBlank(name)) {
            throw new BusinessException("名称不能为空");
        }
        String camelName = name.substring(0, 1).toLowerCase() + name.substring(1);
        String projectDir = System.getProperty(USER_DIR);
        String templateDir = projectDir + TEST_RESOURCES_DIR + TEMPLATE_DIR;
        Properties p = new Properties();
        // 设置模板加载路径 为D盘 work文件夹
        p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templateDir);
        Velocity.init(p);
        // controller
        String controllerName = name + CONTROLLER_SUFFIX;
        String controllerPackage;
        if (StringUtils.isNotBlank(moduleName)) {
            controllerPackage = PACKAGE_NAME + "." + moduleName + CONTROLLER_PACKAGE_NAME;
        } else {
            controllerPackage = PACKAGE_NAME + CONTROLLER_PACKAGE_NAME;
        }
        String controllerPackagePath = controllerPackage.replaceAll("\\.", File.separator);
        String controllerOutFileDir = projectDir + JAVA_DIR + controllerPackagePath;
        String controllerOutFileName = controllerName + DOT_JAVA;
        // service
        String serviceName = name + SERVICE_SUFFIX;
        String serviceCamelName = serviceName.substring(0, 1).toLowerCase() + serviceName.substring(1);
        String servicePackage;
        if (StringUtils.isNotBlank(moduleName)) {
            servicePackage = PACKAGE_NAME + "." + moduleName + SERVICE_PACKAGE_NAME;
        } else {
            servicePackage = PACKAGE_NAME + SERVICE_PACKAGE_NAME;
        }
        String servicePackagePath = servicePackage.replaceAll("\\.", File.separator);
        String serviceOutFileDir = projectDir + JAVA_DIR + servicePackagePath;
        String serviceOutFileName = serviceName + DOT_JAVA;
        // serviceImpl
        String serviceImplName = name + SERVICE_IMPL_SUFFIX;
        String serviceImplPackage;
        if (StringUtils.isNotBlank(moduleName)) {
            serviceImplPackage = PACKAGE_NAME + "." + moduleName + SERVICE_IMPL_PACKAGE_NAME;
        } else {
            serviceImplPackage = PACKAGE_NAME + SERVICE_IMPL_PACKAGE_NAME;
        }
        String serviceImplPackagePath = serviceImplPackage.replaceAll("\\.", File.separator);
        String serviceImplOutFileDir = projectDir + JAVA_DIR + serviceImplPackagePath;
        String serviceImplOutFileName = serviceImplName + DOT_JAVA;
        // mapper
        String mapperName = name + MAPPER_SUFFIX;
        String mapperCamelName = mapperName.substring(0, 1).toLowerCase() + mapperName.substring(1);
        String mapperPackage;
        if (StringUtils.isNotBlank(moduleName)) {
            mapperPackage = PACKAGE_NAME + "." + moduleName + MAPPER_PACKAGE_NAME;
        } else {
            mapperPackage = PACKAGE_NAME + MAPPER_PACKAGE_NAME;
        }
        String mapperPackagePath = mapperPackage.replaceAll("\\.", File.separator);
        String mapperOutFileDir = projectDir + JAVA_DIR + mapperPackagePath;
        String mapperOutFileName = mapperName + DOT_JAVA;
        // mapper xml
        String mapperXmlOutFileDir;
        if (StringUtils.isNotBlank(moduleName)) {
            mapperXmlOutFileDir = projectDir + MAPPER_XML_DIR + moduleName + File.separator;
        } else {
            mapperXmlOutFileDir = projectDir + MAPPER_XML_DIR;
        }
        String mapperXmlOutFileName = mapperName + DOT_XML;
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("camelName", camelName);
        params.put("desc", desc);
        params.put("author", author);
        params.put("createDate", DateUtil.formatDate(new Date()));
        params.put("controllerName", controllerName);
        params.put("controllerPackage", controllerPackage);
        params.put("serviceName", serviceName);
        params.put("serviceCamelName", serviceCamelName);
        params.put("servicePackage", servicePackage);
        params.put("serviceImplName", serviceImplName);
        params.put("serviceImplPackage", serviceImplPackage);
        params.put("mapperName", mapperName);
        params.put("mapperCamelName", mapperCamelName);
        params.put("mapperPackage", mapperPackage);
        params.put("generateController", generateController);
        params.put("generateService", generateService);
        params.put("generateMapper", generateMapper);
        if (generateController) {
            render(CONTROLLER_TEMPLATE_NAME, params, controllerOutFileDir, controllerOutFileName);
        }
        if (generateService) {
            render(SERVICE_TEMPLATE_NAME, params, serviceOutFileDir, serviceOutFileName);
            render(SERVICE_IMPL_TEMPLATE_NAME, params, serviceImplOutFileDir, serviceImplOutFileName);
        }
        if (generateMapper) {
            render(MAPPER_TEMPLATE_NAME, params, mapperOutFileDir, mapperOutFileName);
            render(MAPPER_XML_TEMPLATE_NAME, params, mapperXmlOutFileDir, mapperXmlOutFileName);
        }
    }

    public static void render(String templateName, Map<String, Object> params, String outFileDirPath, String outFileName) throws Exception {
        if (StringUtils.isBlank(templateName)) {
            throw new BusinessException("模板名称不能为空");
        }
        if (MapUtils.isEmpty(params)) {
            throw new BusinessException("模板参数不能为空");
        }
        if (StringUtils.isBlank(outFileDirPath)) {
            throw new BusinessException("文件输出目录路径不能为空");
        }
        if (StringUtils.isBlank(outFileName)) {
            throw new BusinessException("文件输出名称不能为空");
        }
        File outFieDir = new File(outFileDirPath);
        if (!outFieDir.exists()) {
            outFieDir.mkdirs();
        }
        File outFile = new File(outFieDir, outFileName);
        VelocityContext context = new VelocityContext(params);
        Template template = Velocity.getTemplate(templateName, "UTF-8");
        Writer writer = new PrintWriter(new FileOutputStream(outFile));
        template.merge(context, writer);
        writer.flush();
        writer.close();
    }

}
