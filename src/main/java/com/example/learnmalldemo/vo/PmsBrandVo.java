package com.example.learnmalldemo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("品牌视图")
public class PmsBrandVo {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "品牌名")
    private String name;

    @ApiModelProperty(value = "首字母")
    private String firstLetter;

    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;

    @ApiModelProperty(value = "是否显示状态")
    private Integer showStatus;

    @ApiModelProperty(value = "产品数量")
    private Integer productCount;

    @ApiModelProperty(value = "产品评论数量")
    private Integer productCommentCount;

    @ApiModelProperty(value = "品牌logo")
    private String logo;

    @ApiModelProperty(value = "专区大图")
    private String bigPic;

    @ApiModelProperty(value = "品牌故事")
    private String brandStory;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
