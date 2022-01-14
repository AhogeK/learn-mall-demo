package com.example.learnmalldemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author AhogeK
 * @since 2021-04-15
 */
@Data
@Schema(description = "品牌表")
public class PmsBrand {

    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "品牌名")
    private String name;

    @Schema(description = "首字母")
    private String firstLetter;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;

    @Schema(description = "是否显示状态")
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

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private LocalDateTime updateTime;

    @TableLogic
    @Schema(description = "是否删除")
    private Integer isDeleted;
}