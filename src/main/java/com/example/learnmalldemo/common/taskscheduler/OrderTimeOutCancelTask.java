package com.example.learnmalldemo.common.taskscheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * 订单超时取消并解锁库存的定时器
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 09:29
 */
@Slf4j
public class OrderTimeOutCancelTask implements Runnable {

    @Override
    public void run() {
        log.info("取消订单，并根据sku编号释放锁定库存");
    }
}
