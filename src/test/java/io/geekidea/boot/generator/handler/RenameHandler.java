package io.geekidea.boot.generator.handler;

import io.geekidea.boot.generator.constant.GenerateConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 重命名和替换包名称
 *
 * @author geekidea
 * @date 2023/11/24
 **/
@Slf4j
public class RenameHandler {

    /**
     * @param currentPackageName
     * @param targetPackageName
     * @param targetArtifactId
     * @throws Exception
     */
    public static void execute(String currentPackageName, String targetPackageName, String targetArtifactId) throws Exception {
        log.info("currentPackageName:" + currentPackageName);
        log.info("targetPackageName:" + targetPackageName);
        log.info("targetArtifactId:" + targetArtifactId);
        if (StringUtils.isBlank(currentPackageName)) {
            throw new RuntimeException("当前包名称不能为空");
        }
        if (StringUtils.isBlank(targetPackageName)) {
            throw new RuntimeException("目标包名称不能为空");
        }
        if (StringUtils.isBlank(targetArtifactId)) {
            targetArtifactId = GenerateConstant.DEFAULT_PROJECT_NAME;
        }
        // 校验包名格式
        if (currentPackageName.equals(targetPackageName)) {
            throw new RuntimeException("包名称一致，无须修改");
        }
        String projectPath = System.getProperty("user.dir");
        log.info("projectPath = " + projectPath);
        String currentPackagePath = currentPackageName.replaceAll("\\.", File.separator);
        String targetPackagePath = targetPackageName.replaceAll("\\.", File.separator);
        log.info("currentPackagePath = " + currentPackagePath);

        // 替换pom.xml内容
        log.info("替换pom.xml内容开始");
        String pomXmlPath = projectPath + "/" + GenerateConstant.POM_XML;
        File pomXmlFile = new File(pomXmlPath);
        replacePomXml(pomXmlFile, targetPackageName, targetArtifactId);
        log.info("替换pom.xml内容结束");

        // 替换application.yml内容
        log.info("替换application.yml内容开始");
        String applicationYmlPath = projectPath + "/" + GenerateConstant.APPLICATION_YML;
        File applicationYmlFile = new File(applicationYmlPath);
        replaceApplicationYml(applicationYmlFile, targetArtifactId);
        log.info("替换application.yml内容结束");

        // 替换xml中的包名称
        log.info("替换xml中的包名称开始");
        String mapperDir = projectPath + GenerateConstant.SRC_MAIN_RESOURCES_MAPPER;
        File mapperXmlParentFile = new File(mapperDir);
        recursionReplacePackageName(mapperXmlParentFile, currentPackageName, targetPackageName, GenerateConstant.DOT_XML);
        log.info("替换xml中的包名称结束");

        // 重命名main包名称
        log.info("重命名main包名称开始");
        File targetMainParentFile = renameMainJavaPackage(projectPath, currentPackagePath, targetPackagePath);
        log.info("重命名main包名称结束");
        recursionReplacePackageName(targetMainParentFile, currentPackageName, targetPackageName, GenerateConstant.DOT_JAVA);

        // 重命名test包名称
        File targetTestParentFile = renameTestJavaPackage(projectPath, currentPackagePath, targetPackagePath);
        recursionReplacePackageName(targetTestParentFile, currentPackageName, targetPackageName, GenerateConstant.DOT_JAVA);

    }

    /**
     * 重命名src/main/java下的包名称
     *
     * @param projectPath
     * @param currentPackagePath
     * @param targetPackagePath
     * @return
     * @throws Exception
     */
    public static File renameMainJavaPackage(String projectPath, String currentPackagePath, String targetPackagePath) throws Exception {
        String srcParentPackage = projectPath + GenerateConstant.SRC_MAIN_JAVA + currentPackagePath;
        String targetSrcParentPackage = projectPath + GenerateConstant.SRC_MAIN_JAVA + targetPackagePath;
        return renameJavaPackage(srcParentPackage, targetSrcParentPackage);
    }

    /**
     * 重命名src/test/java下的包名称
     *
     * @param projectPath
     * @param currentPackagePath
     * @param targetPackagePath
     * @return
     * @throws Exception
     */
    public static File renameTestJavaPackage(String projectPath, String currentPackagePath, String targetPackagePath) throws Exception {
        String srcParentPackage = projectPath + GenerateConstant.SRC_TEST_JAVA + currentPackagePath;
        String targetSrcParentPackage = projectPath + GenerateConstant.SRC_TEST_JAVA + targetPackagePath;
        return renameJavaPackage(srcParentPackage, targetSrcParentPackage);
    }

    /**
     * 重命名java包名称
     *
     * @param srcParentPackage
     * @param targetSrcParentPackage
     * @return
     * @throws Exception
     */
    public static File renameJavaPackage(String srcParentPackage, String targetSrcParentPackage) throws Exception {
        File srcParentFile = new File(srcParentPackage);
        File targetSrcParentFile = new File(targetSrcParentPackage);
        log.info("srcParentFile:" + srcParentFile);
        log.info("targetSrcParentFile:" + targetSrcParentFile);
        if (!targetSrcParentFile.exists()) {
            log.info("创建目录成功：" + targetSrcParentFile);
            targetSrcParentFile.mkdirs();
        }
        boolean flag = srcParentFile.renameTo(targetSrcParentFile);
        if (flag) {
            log.info("重命名目录成功：" + targetSrcParentFile);
        } else {
            throw new RuntimeException("目录替换失败");
        }
        return targetSrcParentFile;
    }


    /**
     * 替换源代码中的包路径
     *
     * @param targetFile
     */
    public static void recursionReplacePackageName(File targetFile, String currentPackageName, String targetPackageName, String fileSuffix) throws Exception {
        if (targetFile == null) {
            return;
        }
        if (!targetFile.exists()) {
            return;
        }
        boolean directory = targetFile.isDirectory();
        // 如果是目录，则继续递归
        if (directory) {
            File[] files = targetFile.listFiles();
            if (ArrayUtils.isNotEmpty(files)) {
                for (File file : files) {
                    recursionReplacePackageName(file, currentPackageName, targetPackageName, fileSuffix);
                }
            }
        } else {
            // 判断是否是.java的文件，是则操作，否则跳过
            String name = targetFile.getName();
            if (name.endsWith(fileSuffix)) {
                // 如果是指定文件，则读取文件中的内容，进行替换
                replaceAllPackageName(targetFile, currentPackageName, targetPackageName);
                log.info("替换目标文件包名称：" + targetFile);
            }
        }

    }

    /**
     * 替换文件内容
     *
     * @param file
     * @param currentPackageName
     * @param targetPackageName
     * @throws Exception
     */
    public static void replaceAllPackageName(File file, String currentPackageName, String targetPackageName) throws Exception {
        String content = FileUtils.readFileToString(file, GenerateConstant.UTF8);
        if (StringUtils.isNotBlank(content)) {
            content = content.replaceAll(currentPackageName, targetPackageName);
            // 重写文件内容
            FileUtils.writeStringToFile(file, content, GenerateConstant.UTF8, false);
        }
    }

    /**
     * 替换pom.xml内容
     *
     * @param file
     * @param targetPackageName
     * @param targetArtifactId
     * @throws Exception
     */
    public static void replacePomXml(File file, String targetPackageName, String targetArtifactId) throws Exception {
        List<String> lines = FileUtils.readLines(file, GenerateConstant.UTF8);
        if (CollectionUtils.isNotEmpty(lines)) {
            String groupIdElement = "\t<groupId>" + targetPackageName + "</groupId>";
            String artifactIdElement = "\t<artifactId>" + targetArtifactId + "</artifactId>";
            String nameElement = "\t<name>" + targetArtifactId + "</name>";
            lines.set(11, groupIdElement);
            lines.set(12, artifactIdElement);
            lines.set(15, nameElement);
            // 重写文件内容
            FileUtils.writeLines(file, GenerateConstant.UTF8, lines, System.lineSeparator(), false);
            log.info("pom.xml文件内容替换成功");
        }
    }

    /**
     * 替换application.yml内容
     *
     * @param file
     * @param targetArtifactId
     * @throws Exception
     */
    public static void replaceApplicationYml(File file, String targetArtifactId) throws Exception {
        List<String> lines = FileUtils.readLines(file, GenerateConstant.UTF8);
        List<String> targetLines = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(lines)) {
            for (String line : lines) {
                String trimLine = line.trim();
                if (trimLine.startsWith("projectPrefix")) {
                    line = "    projectPrefix: " + targetArtifactId;
                }
                targetLines.add(line);
            }
            // 重写文件内容
            FileUtils.writeLines(file, GenerateConstant.UTF8, targetLines, System.lineSeparator(), false);
            log.info("pom.xml文件内容替换成功");
        }
    }

}
