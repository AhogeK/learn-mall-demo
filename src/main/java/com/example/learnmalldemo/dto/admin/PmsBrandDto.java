package com.example.learnmalldemo.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "品牌表单")
public class PmsBrandDto {

    @Schema(description = "品牌名")
    @NotNull(message = "{notnull}")
    private String name;

    @Schema(description = "首字母")
    private String firstLetter;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;

    @Schema(description = "收否显示状态")
    private Integer showStatus;

    @Schema(description = "产品数量")
    private Integer productCount;

    @Schema(description = "产品评论数量")
    private Integer productCommentCount;

    @Schema(description = "品牌logo")
    private String logo;

    @Schema(description = "专区大图")
    private String bigPic;

    @Schema(description = "品牌故事")
    private String brandStory;
}
