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
import org.fusesource.jansi.Ansi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * <p>
 * 打印项目信息
 * </p>
 *
 * @author geekidea
 * @date 2019-05-08
 **/
@Slf4j
public class PrintApplicationInfo {

    /**
     * 执行之前，打印前置条件提示
     */
    public static void printTip(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        // 项目profile
        String profileActive = environment.getProperty("spring.profiles.active");
        StringBuffer tip = new StringBuffer();
        tip.append("===========================================================================================\n");
        tip.append("                                                                                  \n");
        tip.append("                               !!!准备工作!!!                                      \n");
        tip.append(" 1.导入SQL初始化脚本：docs/db，根据不同数据库导入对应SQL脚本并修改链接等信息配置\n");
        tip.append(" 2.启动Redis服务，必要条件\n");
        tip.append(" 3.启动SpringBootAdmin Server，可选操作，admin模块中，启动SpringBootPlusAdminApplication\n");
        tip.append(" 4.根据项目需要，修改项目配置，请先查看官网配置文档：https://springboot.plus/config/\n");
        tip.append(" 5.项目模块说明：\n");
        tip.append("    admin：       SpringBootAdmin Server启动模块\n");
        tip.append("    bootstrap：   项目启动模块\n");
        tip.append("    config：      项目配置模块\n");
        tip.append("    distribution：项目打包模块，打包时，请先选中Maven Profiles中的release和对应环境\n");
        tip.append("    example：     业务自定义模块，自己的业务代码可在example下进行，也可以再创建模块\n");
        tip.append("    framework：   项目核心框架模块\n");
        tip.append("    generator：   代码生成模块，启动类：SpringBootPlusGenerator，请根据实际情况进行配置\n");
        tip.append("    scheduled：   任务调度模块\n");
        tip.append("    system：      系统管理模块\n");
        tip.append(" 6.FAQ：https://springboot.plus/faq\n");
        tip.append(" 7.如开发中遇到bug及问题，欢迎提交ISSUES：https://github.com/geekidea/spring-boot-plus/issues\n");
        tip.append(" 8.QQ：625301326，进群答案：springboot.plus\n");
        tip.append("                                                                                  \n");
        tip.append("===========================================================================================\n");
        if ("dev".equals(profileActive)) {
            log.info("\n{}", Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(tip.toString()).reset().toString());
        }
    }

    /**
     * 启动成功之后，打印项目信息
     */
    public static void print(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        // 项目名称
        String projectFinalName = environment.getProperty("info.project-finalName");
        // 项目版本
        String projectVersion = environment.getProperty("info.project-version");
        // 项目profile
        String profileActive = environment.getProperty("spring.profiles.active");
        // 项目路径
        String contextPath = environment.getProperty("server.servlet.context-path");
        // 项目IP或域名地址
        String serverIp = environment.getProperty("spring-boot-plus.server-ip");
        // 项目端口
        String port = environment.getProperty("server.port");
        // Spring Boot Admin Server地址，请先在admin模块中启动 SpringBootPlusAdminApplication
        String springBootAdminServerUrl = environment.getProperty("spring.boot.admin.client.url");

        log.info("projectFinalName : {}", projectFinalName);
        log.info("projectVersion : {}", projectVersion);
        log.info("profileActive : {}", profileActive);
        log.info("contextPath : {}", contextPath);
        log.info("serverIp : {}", serverIp);
        log.info("port : {}", port);

        String startSuccess = " ____    __                    __        ____                                                   \n" +
                "/\\  _`\\ /\\ \\__                /\\ \\__    /\\  _`\\                                                 \n" +
                "\\ \\,\\L\\_\\ \\ ,_\\    __     _ __\\ \\ ,_\\   \\ \\,\\L\\_\\  __  __    ___    ___     __    ____    ____  \n" +
                " \\/_\\__ \\\\ \\ \\/  /'__`\\  /\\`'__\\ \\ \\/    \\/_\\__ \\ /\\ \\/\\ \\  /'___\\ /'___\\ /'__`\\ /',__\\  /',__\\ \n" +
                "   /\\ \\L\\ \\ \\ \\_/\\ \\L\\.\\_\\ \\ \\/ \\ \\ \\_     /\\ \\L\\ \\ \\ \\_\\ \\/\\ \\__//\\ \\__//\\  __//\\__, `\\/\\__, `\\\n" +
                "   \\ `\\____\\ \\__\\ \\__/.\\_\\\\ \\_\\  \\ \\__\\    \\ `\\____\\ \\____/\\ \\____\\ \\____\\ \\____\\/\\____/\\/\\____/\n" +
                "    \\/_____/\\/__/\\/__/\\/_/ \\/_/   \\/__/     \\/_____/\\/___/  \\/____/\\/____/\\/____/\\/___/  \\/___/ \n" +
                "                                                                                                \n" +
                "                                                                                                ";

        String homeUrl = "http://" + serverIp + ":" + port + contextPath;
        String swaggerUrl = "http://" + serverIp + ":" + port + contextPath + "/swagger-ui.html";
        String knife4jUrl = "http://" + serverIp + ":" + port + contextPath + "/doc.html";
        log.info("Admin:   {}", springBootAdminServerUrl);
        log.info("Home:    {}", homeUrl);
        log.info("Knife4j: {}", knife4jUrl);
        log.info("Swagger: {}", swaggerUrl);
        log.info("spring-boot-plus project start success...........");
        if ("dev".equals(profileActive)) {
            log.info("\n{}", AnsiUtil.getAnsi(Ansi.Color.BLUE, startSuccess));
        } else {
            log.info("\n{}", startSuccess);
        }
    }

}
