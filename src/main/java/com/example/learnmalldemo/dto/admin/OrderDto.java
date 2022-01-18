package com.example.learnmalldemo.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 生成订单所需要的参数
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 16:05
 */
@Data
@Schema(description = "生成订单所需要的参数")
public class OrderDto {

    /**
     * 收获地址id
     */
    @Schema(description = "收获地址id")
    private Long memberReceiveAddressId;

    /**
     * 优惠券id
     */
    @Schema(description = "优惠券id")
    private Long couponId;

    /**
     * 使用的积分数
     */
    @Schema(description = "使用的积分数")
    private Integer useIntegration;

    /**
     * 支付方式
     */
    @Schema(description = "支付方式")
    private Integer payType;
}
