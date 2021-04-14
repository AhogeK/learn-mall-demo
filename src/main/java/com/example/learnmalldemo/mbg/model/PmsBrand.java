package com.example.learnmalldemo.mbg.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌实体
 *
 * @author ahogek
 * @date 2021-04-14 16:40
 */
@Data
@ApiModel("品牌信息")
public class PmsBrand implements Serializable {

    @ApiModelProperty("品牌id")
    private Long id;

    @ApiModelProperty("品牌名")
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

    @ApiModelProperty("序列化id")
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", firstLetter=").append(firstLetter);
        sb.append(", sort=").append(sort);
        sb.append(", factoryStatus=").append(factoryStatus);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", productCount=").append(productCount);
        sb.append(", productCommentCount=").append(productCommentCount);
        sb.append(", logo=").append(logo);
        sb.append(", bigPic=").append(bigPic);
        sb.append(", brandStory=").append(brandStory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}