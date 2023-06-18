package io.geekidea.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlusApplication.class, args);
        System.out.println("http://localhost:8888/doc.html");
        System.out.println("http://localhost:8888/swagger-ui/index.html");
        System.out.println("账号：admin");
        System.out.println("密码：123456");
        System.out.println("swagger密码：e10adc3949ba59abbe56e057f20f883e");
        System.out.println("  _____ _______       _____ _______    _____ _    _  _____ _____ ______  _____ _____ \n" +
                " / ____|__   __|/\\   |  __ \\__   __|  / ____| |  | |/ ____/ ____|  ____|/ ____/ ____|\n" +
                "| (___    | |  /  \\  | |__) | | |    | (___ | |  | | |   | |    | |__  | (___| (___  \n" +
                " \\___ \\   | | / /\\ \\ |  _  /  | |     \\___ \\| |  | | |   | |    |  __|  \\___ \\\\___ \\ \n" +
                " ____) |  | |/ ____ \\| | \\ \\  | |     ____) | |__| | |___| |____| |____ ____) |___) |\n" +
                "|_____/   |_/_/    \\_\\_|  \\_\\ |_|    |_____/ \\____/ \\_____\\_____|______|_____/_____/ \n");
    }

}
