package com.example.learnmalldemo.service.mq;

import com.example.learnmalldemo.form.OrderForm;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理 Service
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 15:57
 */
public interface IOmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     *
     * @param orderForm 订单生成参数
     */
    @Transactional(rollbackFor = Exception.class)
    Long generateOrder(OrderForm orderForm);

    /**
     * 取消单个超时订单
     *
     * @param orderId 订单id
     */
    @Transactional(rollbackFor = Exception.class)
    void cancelOrder(Long orderId);
}
