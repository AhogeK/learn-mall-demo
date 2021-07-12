package com.example.learnmalldemo.form;

import lombok.Data;

/**
 * 生成订单所需要的参数
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 16:05
 */
@Data
public class OrderForm {

    /**
     * 收获地址id
     */
    private Long memberReceiveAddressId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 使用的积分数
     */
    private Integer useIntegration;

    /**
     * 支付方式
     */
    private Integer payType;
}
