package com.example.learnmalldemo.common.taskscheduler;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 任务启动器
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 09:35
 */
@Component
public class ThreadPoolTaskSchedulerStater {

    private final ThreadPoolTaskScheduler taskScheduler;

    public ThreadPoolTaskSchedulerStater(ThreadPoolTaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void scheduleRunnableWithPeriodicTrigger() {
        taskScheduler.schedule(new OrderTimeOutCancelTask(), new PeriodicTrigger(10, TimeUnit.MINUTES));
    }
}
