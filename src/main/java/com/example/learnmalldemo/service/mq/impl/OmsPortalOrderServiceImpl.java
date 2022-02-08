package com.example.learnmalldemo.service.mq.impl;

import com.example.learnmalldemo.common.mq.sender.CancelOrderSender;
import com.example.learnmalldemo.dto.admin.OrderDto;
import com.example.learnmalldemo.service.mq.IOmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理 Service 实现类
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 16:09
 */
@Slf4j
@Service
public class OmsPortalOrderServiceImpl implements IOmsPortalOrderService {

    private final CancelOrderSender cancelOrderSender;

    public OmsPortalOrderServiceImpl(CancelOrderSender cancelOrderSender) {
        this.cancelOrderSender = cancelOrderSender;
    }

    @Override
    public Long generateOrder(OrderDto orderDto) {
        log.info("执行订单生成");
        // 成功下了一个订单后开启一个延迟消息，用户用户没有付款时取消订单，orderId在下单后生成
        sendDelayMessageCancelOrder();
        return 1325379L;
    }

    /**
     * 发送延迟消息
     *
     */
    private void sendDelayMessageCancelOrder() {
        // 获取订单超时时间，测试可以短一点
        long delayTimes = 30L * 1000;
        // 发送延迟消息
        cancelOrderSender.sendMessage(1325379L, delayTimes);
    }

    @Override
    public void cancelOrder(Long orderId) {
        log.info("执行取消订单，订单id：{}", orderId);
    }
}
