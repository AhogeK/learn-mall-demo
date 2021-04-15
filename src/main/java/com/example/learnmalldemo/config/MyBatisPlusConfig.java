package com.example.learnmalldemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
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
@MapperScan(basePackages = "com.example.learnmalldemo.mapper")
public class MyBatisPlusConfig {

    /**
     * Mybatis 分页配置
     *
     * @return MP 拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
