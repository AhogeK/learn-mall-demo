package com.example.learnmalldemo.entity.admin;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品属性参数表
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @since 2021-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "商品属性参数表")
public class PmsProductAttribute {

    @TableId
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "产品属性分类表id")
    private Long productAttributeCategoryId;

    @Schema(description = "产品属性名称")
    private String name;

    @Schema(description = "属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;

    @Schema(description = "属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;

    @Schema(description = "可选值列表，以逗号隔开")
    private String inputList;

    @Schema(description = "排序字段：最高的可以单独上传图片")
    private Integer sort;

    @Schema(description = "分类筛选样式：1->普通；1->颜色")
    private Integer filterType;

    @Schema(description = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Integer searchType;

    @Schema(description = "相同属性产品是否关联；0->不关联；1->关联")
    private Integer relatedStatus;

    @Schema(description = "是否支持手动新增；0->不支持；1->支持")
    private Integer handAddStatus;

    @Schema(description = "属性的类型；0->规格；1->参数")
    private Integer type;


}
