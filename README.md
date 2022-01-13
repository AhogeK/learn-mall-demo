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
8. 添加 RabbitMQ
   * 当中会有不少 RabbitMQ 的术语和知识，需要了解RabbitMQ文档。如 Exchange、Queue，以及 Queue 上的 argument 参数，具体都可以在
     [RabbitMQ官方文档上进行详细的了解](https://www.rabbitmq.com/documentation.html)
   * 简单入手可以参考 [Spring Boot RabbitMQ – Complete Guide For Beginners](https://springhow.com/spring-boot-rabbitmq/)

## 准备内容

### MySQL

#### Dockfile

```dockerfile
FROM mysql:latest
COPY mall.sql /mall.sql
```

[mall.sql 下载地址](https://github.com/macrozheng/mall/blob/master/document/sql/mall.sql)

**下载完的 .sql 文件在最上面添加 一条 ``USE mall;``**

#### Docker compose

* docker-compose.yml
   * 该文件在 mysql 文件夹同级

```yaml
version: "3.9"
services:
   # 指定服务器名称
   mall-db:
      build: mysql/.
      # 指定服务使用的镜像
      image: mysql_mall-db
      # 指定容器的名称
      container_name: mall-mysql
      # 指定容器的环境变量
      environment:
         - MYSQL_DATABASE=mall
      # 执行指令
      command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
      command: mysqld --init-file="/mall.sql"
      # 重启设定
      restart: always
      # 指定服务运行的端口
      ports:
         - 3306:3306
      # 指定容器中要挂载的文件
      volumes:
         - /data/docker/learn/mall/docker-compose/mysql/log:/var/log/mysql
         - /data/docker/learn/mall/docker-compose/mysql/data:/var/lib/mysql
         - /data/docker/learn/mall/docker-compose/mysql/conf:/etc/mysql/conf.d
```

该目录下 包含三个挂在目录:

* conf/
* data/
* log/

以及两个个文件

* Dockerfile
* mall.sql

容器的mysql

``docker exec -it mall-mysql mysql -uroot``

**生成的本地容器mysql只允许本地连且不含密码可直接进入**

修改数据库连接密码

``create user 'root'@'%' identified by '123456';grant all privileges on *.* to 'root'@'%';flush privileges;``

### redis

1. 创建文件夹 ``redis``
2. 创建 ``Dockerfile``
   1. ```dockerfile
      FROM redis
      COPY redis.conf /usr/local/etc/redis/redis.conf
      CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]
      CMD [ "redis-server", "--appendonly", "yes" ]
      ```
3. 创建空文件 ``redis.conf`` 用于后续可自定义配置
4. 在 ``docker-compose.yml`` 添加redis容器相关内容
   1. ```yaml
      version: "3.9"
      services:
      # 指定服务器名称
      mall-db:
        build: mysql/.
        # 指定服务使用的镜像
        image: mysql_mall-db
        # 指定容器的名称
        container_name: mall-mysql
        # 指定容器的环境变量
        environment:
          - MYSQL_DATABASE=mall
        # 执行指令
        command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
        command: mysqld --init-file="/mall.sql"
        # 重启设定
        restart: always
        # 指定服务运行的端口
        ports:
          - 3306:3306
        # 指定容器中要挂载的文件
        volumes:
          - /data/docker/learn/mall/docker-compose/mysql/log:/var/log/mysql
          - /data/docker/learn/mall/docker-compose/mysql/data:/var/lib/mysql
          - /data/docker/learn/mall/docker-compose/mysql/conf:/etc/mysql/conf.d
      mall-redis:
        build: redis/.
        image: redis_mall-redis
        container_name: mall-redis
        volumes:
          - /data/docker/learn/mall/docker-compose/redis/data:/data #数据文件挂载
          - /data/docker/learn/mall/docker-compose/redis/redis.conf:/usr/local/etc/redis/redis.conf
        ports:
          - 6379:6379
      ```

### Nginx

1. 创建 ``nginx`` 文件夹
2. 创建 ``Dockerfile``
   1. ```dockerfile
      FROM nginx
      COPY nginx.conf /etc/nginx/nginx.conf
      ADD conf.d /etc/nginx/
      ```
3. 下载 [nginx.conf](https://github.com/macrozheng/mall/blob/master/document/docker/nginx.conf) 并在 **http
   大括号中下方添加 ``include conf.d/*.conf;``**
4. 创建 ``conf.d`` 文件夹
5. 创建 ``html`` 文件夹
6. 创建 ``log`` 文件夹
7. 编辑 ``docker-compose.yml``
   1. ```yaml
      version: "3.9"
      services:
        # 指定服务器名称
        mall-db:
          build: mysql/.
          # 指定服务使用的镜像
          image: mysql_mall-db
          # 指定容器的名称
          container_name: mall-mysql
          # 指定容器的环境变量
          environment:
            - MYSQL_DATABASE=mall
          # 执行指令
          command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
          command: mysqld --init-file="/mall.sql"
          # 重启设定
          restart: always
          # 指定服务运行的端口
          ports:
            - 3306:3306
          # 指定容器中要挂载的文件
          volumes:
            - /data/docker/learn/mall/docker-compose/mysql/log:/var/log/mysql
            - /data/docker/learn/mall/docker-compose/mysql/data:/var/lib/mysql
            - /data/docker/learn/mall/docker-compose/mysql/conf:/etc/mysql/conf.d
        mall-redis:
          build: redis/.
          image: redis_mall-redis
          container_name: mall-redis
          volumes:
            - /data/docker/learn/mall/docker-compose/redis/data:/data #数据文件挂载
            - /data/docker/learn/mall/docker-compose/redis/redis.conf:/usr/local/etc/redis/redis.conf
          ports:
           - 6379:6379
        mall-nginx:
          build: nginx/.
          image: nginx_mall-nginx
          container_name: mall-nginx
          volumes:
            - /data/docker/learn/mall/docker-compose/nginx/nginx.conf:/etc/nginx/nginx.conf
            - /data/docker/learn/mall/docker-compose/nginx/conf.d:/etc/nginx/conf.d
            - /data/docker/learn/mall/docker-compose/nginx/html:/usr/share/nginx/html #静态资源根目录挂载
            - /data/docker/learn/mall/docker-compose/nginx/log:/var/log/nginx #日志文件挂载
          ports:
            - 80:80
      ```

### Rabbitmq

1. 创建 ``data`` 目录
2. 创建 ``log`` 目录
3. 创建 ``rabbitmq.conf`` 文件用于后续自定义配置
4. 创建 ``enabled_plugins`` 文件用于设置需要的插件
5. 编辑 ``docker-compose.yml``
   1. ```yaml
      version: "3.9"
      services:
        # 指定服务器名称
        mall-db:
          build: mysql/.
          # 指定服务使用的镜像
          image: mysql_mall-db
          # 指定容器的名称
          container_name: mall-mysql
          # 指定容器的环境变量
          environment:
            - MYSQL_DATABASE=mall
          # 执行指令
          command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
          command: mysqld --init-file="/mall.sql"
          # 重启设定
          restart: always
          # 指定服务运行的端口
          ports:
            - 3306:3306
          # 指定容器中要挂载的文件
          volumes:
            - /data/docker/learn/mall/docker-compose/mysql/log:/var/log/mysql
            - /data/docker/learn/mall/docker-compose/mysql/data:/var/lib/mysql
            - /data/docker/learn/mall/docker-compose/mysql/conf:/etc/mysql/conf.d
        mall-redis:
          build: redis/.
          image: redis_mall-redis
          container_name: mall-redis
          volumes:
            - /data/docker/learn/mall/docker-compose/redis/data:/data #数据文件挂载
            - /data/docker/learn/mall/docker-compose/redis/redis.conf:/usr/local/etc/redis/redis.conf
          ports:
            - 6379:6379
        mall-nginx:
          build: nginx/.
          image: nginx_mall-nginx
          container_name: mall-nginx
          volumes:
            - /data/docker/learn/mall/docker-compose/nginx/nginx.conf:/etc/nginx/nginx.conf
            - /data/docker/learn/mall/docker-compose/nginx/conf.d:/etc/nginx/conf.d
            - /data/docker/learn/mall/docker-compose/nginx/html:/usr/share/nginx/html #静态资源根目录挂载
            - /data/docker/learn/mall/docker-compose/nginx/log:/var/log/nginx #日志文件挂载
          ports:
            - 80:80
        mall-rabbitmq:
          image: rabbitmq:3.8-management
          container_name: mall-rabbitmq
          volumes:
            - /data/docker/learn/mall/docker-compose/rabbitmq/data:/var/lib/rabbitmq #数据文件挂载
            - /data/docker/learn/mall/docker-compose/rabbitmq/log:/var/log/rabbitmq #日志文件挂载
            - /data/docker/learn/mall/docker-compose/rabbitmq/enabled_plugins:/etc/rabbitmq/enabled_plugins #启用插件文件挂载
            - /data/docker/learn/mall/docker-compose/rabbitmq/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf #配置文件挂载
          ports:
            - 5672:5672
            - 15672:15672
      ```

MQ相关设置 [参考这里](http://www.macrozheng.com/#/deploy/mall_deploy_docker_compose?id=rabbitmq)

### Elasticsearch

1. 创建文件夹 ``data`` 并设置 777 权限
2. 创建文件夹 ``plugins``
3. 编辑 ``docker-compose.yml``
   1. ```yaml
      version: "3.9"
      services:
        # 指定服务器名称
        mall-db:
          build: mysql/.
          # 指定服务使用的镜像
          image: mysql_mall-db
          # 指定容器的名称
          container_name: mall-mysql
          # 指定容器的环境变量
          environment:
            - MYSQL_DATABASE=mall
          # 执行指令
          command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
          command: mysqld --init-file="/mall.sql"
          # 重启设定
          restart: always
          # 指定服务运行的端口
          ports:
            - 3306:3306
          # 指定容器中要挂载的文件
          volumes:
            - /data/docker/learn/mall/docker-compose/mysql/log:/var/log/mysql
            - /data/docker/learn/mall/docker-compose/mysql/data:/var/lib/mysql
            - /data/docker/learn/mall/docker-compose/mysql/conf:/etc/mysql/conf.d
        mall-redis:
          build: redis/.
          image: redis_mall-redis
          container_name: mall-redis
          volumes:
            - /data/docker/learn/mall/docker-compose/redis/data:/data #数据文件挂载
            - /data/docker/learn/mall/docker-compose/redis/redis.conf:/usr/local/etc/redis/redis.conf
          ports:
            - 6379:6379
        mall-nginx:
          build: nginx/.
          image: nginx_mall-nginx
          container_name: mall-nginx
          volumes:
            - /data/docker/learn/mall/docker-compose/nginx/nginx.conf:/etc/nginx/nginx.conf
            - /data/docker/learn/mall/docker-compose/nginx/conf.d:/etc/nginx/conf.d
            - /data/docker/learn/mall/docker-compose/nginx/html:/usr/share/nginx/html #静态资源根目录挂载
            - /data/docker/learn/mall/docker-compose/nginx/log:/var/log/nginx #日志文件挂载
          ports:
            - 80:80
        mall-rabbitmq:
          image: rabbitmq:3.8-management
          container_name: mall-rabbitmq
          volumes:
            - /data/docker/learn/mall/docker-compose/rabbitmq/data:/var/lib/rabbitmq #数据文件挂载
            - /data/docker/learn/mall/docker-compose/rabbitmq/log:/var/log/rabbitmq #日志文件挂载
            - /data/docker/learn/mall/docker-compose/rabbitmq/enabled_plugins:/etc/rabbitmq/enabled_plugins #启用插件文件挂载
            - /data/docker/learn/mall/docker-compose/rabbitmq/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf #配置文件挂载
          ports:
            - 5672:5672
            - 15672:15672
        mall-es:
          image: docker.elastic.co/elasticsearch/elasticsearch:7.16.2
          container_name: mall-es
          environment:
            - node.name=mall-es
            - cluster.name=mall-es-docker-cluster
            - discovery.type=single-node #以单一节点模式启动
          # - discovery.seed_hosts=es02,es03
          # - cluster.initial_master_nodes=mall-es
            - bootstrap.memory_lock=true
            - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
          ulimits:
            memlock:
              soft: -1
              hard: -1
          volumes:
            - /data/docker/learn/mall/docker-compose/es/data:/usr/share/elasticsearch/data
            - /data/docker/learn/mall/docker-compose/es/plugins:/usr/share/elasticsearch/plugins #插件文件挂载
          ports:
            - 9200:9200
      ```

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
            .Builder("jdbc:mysql://192.168.50.190:3406/mall?useUnicode=true&useSSL=false&characterEncoding=utf8",
            "ahogek", "123456");

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