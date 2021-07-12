package com.example.learnmalldemo.entity.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 搜索中的商品属性信息
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 11:30
 */
@Data
public class EsProductAttributeValue {

    private Long id;

    private Long productAttributeId;

    /**
     * 属性值
     */
    @Field(type = FieldType.Keyword)
    private String value;

    /**
     * 属性参数：0->规格；1->参数
     */
    private Integer type;

    /**
     * 属性名称
     */
    @Field(type = FieldType.Keyword)
    private String name;
}
