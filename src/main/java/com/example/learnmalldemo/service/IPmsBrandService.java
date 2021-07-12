package com.example.learnmalldemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.learnmalldemo.entity.PmsBrand;
import com.example.learnmalldemo.form.PmsBrandForm;
import com.example.learnmalldemo.vo.PmsBrandVo;

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
public interface IPmsBrandService extends IService<PmsBrand> {

    /**
     * 获取品牌列表
     *
     * @return 品牌列表
     * @author ahogek
     * @date 2020-04-14 16:58
     */
    List<PmsBrandVo> listAllBrand();

    /**
     * 新建品牌
     *
     * @param brand 品牌实体
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-14 21:21
     */
    void createBrand(PmsBrandForm brand);

    /**
     * 更新品牌
     *
     * @param id    品牌id
     * @param brand 品牌实体
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-14 23:07
     */
    void updateBrand(Long id, PmsBrandForm brand);

    /**
     * 删除品牌
     *
     * @param id 品牌id
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-15 18:22
     */
    void deleteBrand(Long id);

    /**
     * 查询品牌分页
     *
     * @param pmsBrandPage 品牌分页参数
     * @return 品牌分页列表
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-15 22:32
     */
    IPage<PmsBrandVo> getBrandPage(Page<PmsBrand> pmsBrandPage);

    /**
     * 获取指定id品牌
     *
     * @param id 品牌id
     * @return 品牌
     */
    PmsBrand getBrand(Long id);
}
