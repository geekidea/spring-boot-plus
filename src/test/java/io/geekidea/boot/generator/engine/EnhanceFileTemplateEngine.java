package io.geekidea.boot.generator.engine;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author geekidea
 * @date 2023/11/18
 **/
public class EnhanceFileTemplateEngine extends VelocityTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String parentPath = getPathInfo(OutputFile.parent);
        Map<String, Object> cfgMap = (Map<String, Object>) objectMap.get("cfg");
        Object appName = cfgMap.get("appName");
        customFiles.forEach(file -> {
            String filePath = StringUtils.isNotBlank(file.getFilePath()) ? file.getFilePath() : parentPath;
            if (StringUtils.isNotBlank(file.getPackageName())) {
                filePath = filePath + File.separator + file.getPackageName();
                filePath = filePath.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
            }
            String templatePath = file.getTemplatePath();
            String formatFileName;
            String fileName = file.getFileName();
            if (templatePath.startsWith("/templates/app")) {
                if (fileName.contains("%s")) {
                    formatFileName = String.format(fileName, entityName);
                } else {
                    formatFileName = entityName + appName + fileName;
                }
            } else {
                formatFileName = entityName + fileName;
            }
            String filePathName = filePath + File.separator + formatFileName;
            outputFile(new File(filePathName), objectMap, file.getTemplatePath(), file.isFileOverride());
        });
    }
}
