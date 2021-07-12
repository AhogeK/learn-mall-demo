package com.example.learnmalldemo.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户商品浏览历史记录
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 10:50
 */
@Data
@Document
public class MemberReadHistory {

    @Id
    private String id;

    @Indexed
    private Long memberId;

    private String memberNickname;

    private String memberIcon;

    @Indexed
    private Long productId;

    private String productName;

    private String productPic;

    private String productSubTitle;

    private String productPrice;

    private Date createTime;
}
