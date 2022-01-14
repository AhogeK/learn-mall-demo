package com.example.learnmalldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author AhogeK ahogek@gmail.com
 */
@EnableScheduling
@SpringBootApplication
public class LearnMallDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnMallDemoApplication.class, args);
    }
}
