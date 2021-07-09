package com.example.learnmalldemo.common.config;

import com.example.learnmalldemo.common.taskscheduler.ThreadPoolTaskSchedulerStater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 任务线程池配置
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 09:23
 */
@Configuration
@ComponentScan(
        basePackages = "com.example.learnmalldemo.common.taskscheduler",
        basePackageClasses = {ThreadPoolTaskSchedulerStater.class})
public class ThreadPoolTaskSchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}
