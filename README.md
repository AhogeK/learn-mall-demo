# learn-mall-demo

> 个人学习 [macrozheng/mall](https://github.com/macrozheng/mall) 项目，学习商场项目搭建并改造

1. [提前部署准备](doc/提前准备.md)
2. 整合 Spring Boot / Mybatis 同时重构持久框架使用 [Mybatis Plus](https://baomidou.com/guide/)
   * 将通过mbg生成的类改用MP代码生成
   * 将PageHelper改用为MP提供的分页封装
   * Spring Boot 更新为了最新的 2.6.x版本
   * [使用 Mapstruct 来实现 Bean 拷贝](doc/Bean拷贝.md)
3. 整合 springfox swagger
   * 不再使用原项目基于 2.x版本的swagger的 springfox (3.0版本以于2020年停止更新)
   * 将 springfox swagger 替换为了 3.x版本的 ``springdoc-openapi3`` 总体更替了注解的使用
   * 接口按 [REST API](https://www.geeksforgeeks.org/rest-api-architectural-constraints/?ref=lbp) 风格配置
4. 整合 redis 这个与学习项目相同暂未做更改
5. 整合 Spring Security
   * 在整合 Spring Security 前强烈推荐大家阅读 [码农小胖的关于SpingSecurity的个人电子书](https://felord.cn/)
6. 添加定时任务
   * 可以参考学习 [A Guide to the Spring Task Scheduler](https://www.baeldung.com/spring-task-scheduler)
7. 添加 ES 搜索引擎
   * 可以参考学习 [Using Elasticsearch with Spring Boot](https://reflectoring.io/spring-boot-elasticsearch/)
8. 添加 MongoDB
   * 可以参考学习 [Spring Boot MongoDB](https://www.journaldev.com/18156/spring-boot-mongodb)
9. 添加 RabbitMQ
   * 当中会有不少 RabbitMQ 的术语和知识，需要了解RabbitMQ文档。如 Exchange、Queue，以及 Queue 上的 argument 参数，具体都可以在
     [RabbitMQ官方文档上进行详细的了解](https://www.rabbitmq.com/documentation.html)
   * 简单入手可以参考 [Spring Boot RabbitMQ – Complete Guide For Beginners](https://springhow.com/spring-boot-rabbitmq/)

## Mybatis Plus

> mp 版本 v3.5.0 mpg v3.5.1

### 代码生成

```java
package com.example.learnmalldemo.common.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

/**
 * <p>
 * MyBatis Plus 代码生成器
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-15 09:30
 */
public class CodeGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://192.168.50.26:3306/mall?useUnicode=true&useSSL=false&characterEncoding=utf8",
            "root", "123456");

    public static void main(String[] args) {
        /* 代码生成器
         * 3.5.1及以上新代码生成
         */
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入包含的表名？").split(",")))
                .execute();
    }
}
```

上述为 [MPG 新版](https://baomidou.com/pages/779a6e/#%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8) 的代码生成代码，相较与 3.4 的版本简单
了很多，更多详细的配置，可以进各 Config 类中查看有那些详细的配置，这边代码是按最基本默认的配置。