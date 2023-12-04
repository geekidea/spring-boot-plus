package io.geekidea.boot.generator.rename;

import io.geekidea.boot.generator.handler.RenameHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author geekidea
 * @date 2023/11/24
 **/
@Slf4j
public class RenameExecutor {

    public static void main(String[] args) throws Exception {
        // 当前项目包名称
        String currentPackageName = "io.geekidea.boot";
        // 目标项目包名称
        String targetPackageName = "io.geekidea.boot";
        // artifactId名称
        String targetArtifactId = "spring-boot-plus";
        RenameHandler.execute(currentPackageName, targetPackageName, targetArtifactId);
    }

}
