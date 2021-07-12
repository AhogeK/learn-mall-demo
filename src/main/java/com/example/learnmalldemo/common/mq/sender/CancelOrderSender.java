package com.example.learnmalldemo.common.mq.sender;

import com.example.learnmalldemo.common.enumerate.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的发送者
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 15:50
 */
@Slf4j
@Component
public class CancelOrderSender {

    private final AmqpTemplate amqpTemplate;

    public CancelOrderSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(Long orderId, final long delayTimes) {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, message -> {
                    //给消息设置延迟毫秒值
                    message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                    return message;
                });
        log.info("发送延迟消息 orderId:{}", orderId);
    }
}
