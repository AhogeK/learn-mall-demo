package com.example.learnmalldemo.service.es;

import com.example.learnmalldemo.entity.es.EsProduct;
import org.springframework.data.domain.Page;

/**
 * ES 商品管理 Service
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 11:40
 */
public interface IEsProductService {

    /**
     * 商品分页搜索
     *
     * @param keyword  关键字
     * @param pageNum  分页当前页
     * @param pageSize 分页每页大小
     * @return 商品分页
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 导入所有列表
     *
     * @return 导入的条数
     */
    Integer importAllList();

    /**
     * 根据id删除ES中的商品
     *
     * @param id 商品id
     */
    void delete(Long id);

    /**
     * 根据多个id批量删除商品
     *
     * @param ids 多个商品id
     */
    void delete(Long[] ids);

    /**
     * 根据商品id在es中新增商品
     *
     * @param id 商品id
     */
    void create(Long id);
}
