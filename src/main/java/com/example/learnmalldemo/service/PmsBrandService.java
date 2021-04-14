package com.example.learnmalldemo.service;

import com.example.learnmalldemo.mbg.model.PmsBrand;

import java.util.List;

/**
 * <p>
 * 品牌管理服务层
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-31 16:55
 * @since 1.00
 */
public interface PmsBrandService {

    /**
     * 获取品牌列表
     *
     * @return 品牌列表
     * @author ahogek
     * @date 2020-04-14 16:58
     */
    List<PmsBrand> listAllBrand();

    /**
     * 新建品牌
     *
     * @param brand 品牌实体
     * @return 成功数
     */
    int createBrand(PmsBrand brand);

    /**
     * 更新品牌
     *
     * @param id    品牌id
     * @param brand 品牌实体
     * @return 成功数
     */
    int updateBrand(Long id, PmsBrand brand);

    /**
     * 删除品牌
     *
     * @param id 品牌id
     * @return 成功数量
     */
    int deleteBrand(Long id);

    /**
     * 品牌分页
     *
     * @param pageNum  当前页
     * @param pageSize 每页条数
     * @return 品牌分页
     */
    List<PmsBrand> listBrand(int pageNum, int pageSize);

    /**
     * 获取指定id品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    PmsBrand getBrand(Long id);
}
