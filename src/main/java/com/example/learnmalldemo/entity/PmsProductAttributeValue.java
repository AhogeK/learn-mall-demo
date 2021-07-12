package com.example.learnmalldemo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 存储产品参数信息的表
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2021-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PmsProductAttributeValue对象", description = "存储产品参数信息的表")
public class PmsProductAttributeValue {

    @TableId
    @ApiModelProperty(value = "主键id")
    private Long id;

    private Long productId;

    private Long productAttributeId;

    @ApiModelProperty(value = "手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;


}