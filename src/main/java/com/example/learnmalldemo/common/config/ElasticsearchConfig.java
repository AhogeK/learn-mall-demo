package com.example.learnmalldemo.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * ES 配置类
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 13:41
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.learnmalldemo.repository.es")
@ComponentScan(basePackages = {"com.example.learnmalldemo.service.es"})
public class ElasticsearchConfig {

}
