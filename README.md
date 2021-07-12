# learn-mall-demo

> 个人学习 [macrozheng/mall](https://github.com/macrozheng/mall) 项目，学习商场项目搭建并改造

1. 整合 Spring Boot / Mybatis 同时重构持久框架使用 [Mybatis Plus](https://baomidou.com/guide/)
    * 将通过mbg生成的类改用MP代码生成
    * 将PageHelper改用为MP提供的分页封装
2. 整合 springfox swagger 这里将使用最新版,
   注意官方文档的[2.1.3](https://springfox.github.io/springfox/docs/current/#migrating-from-existing-2-x-version) 新版本与老版本的不同
3. 整合 redis 这个与学习项目相同暂未做更改
4. 整合 Spring Security
   * 在整合 Spring Security 前强烈推荐大家阅读 [码农小胖的关于SpingSecurity的个人电子书](https://felord.cn/)
5. 添加定时任务
   * 可以参考学习 [A Guide to the Spring Task Scheduler](https://www.baeldung.com/spring-task-scheduler)
6. 添加 ES 搜索引擎
   * 可以参考学习 [Using Elasticsearch with Spring Boot](https://reflectoring.io/spring-boot-elasticsearch/)
7. 添加 MongoDB
   * 可以参考学习 [Spring Boot MongoDB](https://www.journaldev.com/18156/spring-boot-mongodb)
