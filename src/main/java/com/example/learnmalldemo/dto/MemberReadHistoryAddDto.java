package com.example.learnmalldemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * 会员浏览记录请求表单
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 11:11
 */
@Data
@Schema(description = "会员浏览记录请求表单")
public class MemberReadHistoryAddDto {

    @Indexed
    @Schema(description = "产品id", example = "1")
    private Long productId;

    @Schema(description = "产品名称", example = "银色星芒刺绣网纱底裤")
    private String productName;

    @Schema(description = "产品图片", example = "http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180522/web.png")
    private String productPic;

    @Schema(description = "产品副标题", example = "111")
    private String productSubTitle;

    @Schema(description = "产品价格", example = "100.00")
    private String productPrice;
}
