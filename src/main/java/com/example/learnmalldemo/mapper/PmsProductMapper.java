package com.example.learnmalldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.learnmalldemo.entity.PmsProduct;
import com.example.learnmalldemo.entity.es.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author AhogeK
 * @since 2021-07-09
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    /**
     * 获取所有商品
     *
     * @param id 商品id
     * @return 保存至 ES 的所有商品列表
     */
    List<EsProduct> getProducts(@Param("id") Long id);
}