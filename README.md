# learn-mall-demo

> 个人学习 [macrozheng/mall](https://github.com/macrozheng/mall) 项目，学习商场项目搭建并改造

## 联系方式

- 邮箱 ahogek@gmail.com
- 微信 AhogeK

## 内容

1. [提前部署准备](doc/提前准备.md)
2. 整合 Spring Boot / Mybatis 同时重构持久框架使用 [Mybatis Plus](https://baomidou.com/guide/)
   * [将通过mbg生成的类改用MP代码生成](doc/MybatisPlus.md)
   * 将PageHelper改用为MP提供的分页封装
   * Spring Boot 更新为了最新的 2.6.x版本
   * [使用 Mapstruct 来实现 Bean 拷贝](doc/Bean拷贝.md)
3. 整合 springfox swagger
   * 不再使用原项目基于 2.x 版本的swagger的 springfox (3.0版本以于2020年停止更新)
   * 将 springfox swagger 替换为了 3.x版本的 ``springdoc-openapi3`` 总体更替了注解的使用
   * 接口按 [REST API](https://www.geeksforgeeks.org/rest-api-architectural-constraints/?ref=lbp) 风格配置
4. 整合 redis 这个与学习项目相同暂未做更改
   1. [添加 redis 用户的方式](https://redis.io/commands/acl-setuser)
      1. ```shell
         # 进入redis容器
         docker exec -it mall-redis redis-cli
         # 设置账号
         ACL SETUSER ahogek on allkeys +@all >123456
         # 验证
         AUTH ahogek 123456
         ```
5. 整合 Spring Security
   * [Spring Security quick start](https://spring.io/guides/gs/securing-web/)
   * [Spring Boot Security Auto-Configuration](https://www.baeldung.com/spring-boot-security-autoconfiguration)
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
