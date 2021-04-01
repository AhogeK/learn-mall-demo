package com.example.learnmalldemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * MyBatis 配置类
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-25 21:41
 * @since 1.00
 */
@Configuration
@MapperScan("com.example.learnmalldemo.mbg.mapper")
public class MyBatisConfig {
}
