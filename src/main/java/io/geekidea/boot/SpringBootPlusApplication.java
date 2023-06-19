package io.geekidea.boot;

import io.geekidea.boot.framework.util.IpUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 *
 * @author geekidea
 * @date 2022-3-16
 */
@EnableAsync
@SpringBootApplication
public class SpringBootPlusApplication {

    private static final String BACKSLASH = "/";

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootPlusApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String serverPort = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (!BACKSLASH.equals(contextPath)) {
            contextPath = contextPath + BACKSLASH;
        }
        String localhostUrl = "\nhttp://localhost:" + serverPort + contextPath + "doc.html";
        String ipUrl = "http://" + IpUtil.getLocalhostIp() + ":" + serverPort + contextPath + "doc.html";
        System.out.println(localhostUrl);
        System.out.println(ipUrl);
        System.out.println("账号：admin");
        System.out.println("密码：123456");
        System.out.println("swagger密码：e10adc3949ba59abbe56e057f20f883e\n");
        System.out.println("  _____ _______       _____ _______    _____ _    _  _____ _____ ______  _____ _____ \n" +
                " / ____|__   __|/\\   |  __ \\__   __|  / ____| |  | |/ ____/ ____|  ____|/ ____/ ____|\n" +
                "| (___    | |  /  \\  | |__) | | |    | (___ | |  | | |   | |    | |__  | (___| (___  \n" +
                " \\___ \\   | | / /\\ \\ |  _  /  | |     \\___ \\| |  | | |   | |    |  __|  \\___ \\\\___ \\ \n" +
                " ____) |  | |/ ____ \\| | \\ \\  | |     ____) | |__| | |___| |____| |____ ____) |___) |\n" +
                "|_____/   |_/_/    \\_\\_|  \\_\\ |_|    |_____/ \\____/ \\_____\\_____|______|_____/_____/ \n");
    }

}
