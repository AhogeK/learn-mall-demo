package com.example.learnmalldemo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动类
 *
 * @author AhogeK ahogek@gmail.com
 */
@Slf4j
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class LearnMallDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LearnMallDemoApplication.class, args);
        printSwaggerPath(applicationContext);
    }

    private static void printSwaggerPath(ConfigurableApplicationContext application) {
        try {
            Environment env = application.getEnvironment();
            String ip = InetAddress.getLocalHost().getHostAddress();
            String port = env.getProperty("server.port");
            String path = env.getProperty("server.servlet.context-path");
            String swaggerPath = env.getProperty("springdoc.swagger-ui.path");
            if (StringUtils.isEmpty(path)) {
                path = "";
            }
            log.info("\n\u001B[32m" +
                    "  ████████ ██       ██     ██       ████████    ████████  ████████ ███████  \n" +
                    " ██░░░░░░ ░██      ░██    ████     ██░░░░░░██  ██░░░░░░██░██░░░░░ ░██░░░░██ \n" +
                    "░██       ░██   █  ░██   ██░░██   ██      ░░  ██      ░░ ░██      ░██   ░██ \n" +
                    "░█████████░██  ███ ░██  ██  ░░██ ░██         ░██         ░███████ ░███████  \n" +
                    "░░░░░░░░██░██ ██░██░██ ██████████░██    █████░██    █████░██░░░░  ░██░░░██  \n" +
                    "       ░██░████ ░░████░██░░░░░░██░░██  ░░░░██░░██  ░░░░██░██      ░██  ░░██ \n" +
                    " ████████ ░██░   ░░░██░██     ░██ ░░████████  ░░████████ ░████████░██   ░░██\n" +
                    "░░░░░░░░  ░░       ░░ ░░      ░░   ░░░░░░░░    ░░░░░░░░  ░░░░░░░░ ░░     ░░ " + "\u001B[0m");
            log.info("\n----------------------------------------------------------\n\t" +
                    "Application  is running! Access URLs:\n\t" +
                    "Swagger 访问网址: http://" + ip + ":" + port + path + swaggerPath + "\n" +
                    "----------------------------------------------------------");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
