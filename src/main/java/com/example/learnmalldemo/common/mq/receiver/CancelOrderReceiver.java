package com.example.learnmalldemo.common.mq.receiver;

import com.example.learnmalldemo.service.mq.IOmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 15:54
 */
@Slf4j
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {

    private final IOmsPortalOrderService portalOrderService;

    public CancelOrderReceiver(IOmsPortalOrderService portalOrderService) {
        this.portalOrderService = portalOrderService;
    }

    @RabbitHandler
    public void handle(Long orderId) {
        log.info("收到延迟消息 orderId:{}", orderId);
        portalOrderService.cancelOrder(orderId);
    }
}
