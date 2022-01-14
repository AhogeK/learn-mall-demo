package com.example.learnmalldemo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 品牌视图
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-15 15:34
 * @since 1.00
 */
@Data
@Schema(description = "品牌视图")
public class PmsBrandVo {

    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "品牌名")
    private String name;

    @Schema(description = "首字母")
    private String firstLetter;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
