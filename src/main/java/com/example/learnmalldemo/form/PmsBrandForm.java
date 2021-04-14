package com.example.learnmalldemo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 品牌表单
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-14 21:51
 * @since 1.00
 */
@Data
public class PmsBrandForm {

    @ApiModelProperty("品牌名")
    @NotNull(message = "{notnull}")
    private String name;

    @ApiModelProperty("首字母")
    private String firstLetter;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;

    @ApiModelProperty("收否显示状态")
    private Integer showStatus;

    @ApiModelProperty("产品数量")
    private Integer productCount;

    @ApiModelProperty("产品评论数量")
    private Integer productCommentCount;

    @ApiModelProperty("品牌logo")
    private String logo;

    @ApiModelProperty("专区大图")
    private String bigPic;

    @ApiModelProperty("品牌故事")
    private String brandStory;
}
