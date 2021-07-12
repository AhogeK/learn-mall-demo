package com.example.learnmalldemo.form.mongo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * 会员浏览记录请求表单
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 11:11
 */
@Data
@ApiModel("会员浏览记录请求表单")
public class MemberReadHistoryAddForm {

    @Indexed
    @ApiModelProperty(value = "产品id", example = "1")
    private Long productId;

    @ApiModelProperty(value = "产品名称", example = "银色星芒刺绣网纱底裤")
    private String productName;

    @ApiModelProperty(value = "产品图片", example = "http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png")
    private String productPic;

    @ApiModelProperty(value = "产品副标题", example = "111")
    private String productSubTitle;

    @ApiModelProperty(value = "产品价格", example = "100.00")
    private String productPrice;
}
