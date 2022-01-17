package com.example.learnmalldemo.controller.mq;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.dto.OrderDto;
import com.example.learnmalldemo.service.mq.IOmsPortalOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 订单管理 Controller
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 16:18
 */
@Validated
@RestController
@RequestMapping("/portal-order")
@Tag(name = "订单管理", description = "OmsPortalOrdercontroller")
public class OmsPortalOrderController {


    private final IOmsPortalOrderService omsPortalOrderService;

    public OmsPortalOrderController(IOmsPortalOrderService omsPortalOrderService) {
        this.omsPortalOrderService = omsPortalOrderService;
    }

    @Operation(summary = "根据购物车信息生成订单")
    @PostMapping
    public CommonResult<Long> create(@RequestBody @Valid OrderDto orderDto) {
        return CommonResult.success(omsPortalOrderService.generateOrder(orderDto));
    }
}
