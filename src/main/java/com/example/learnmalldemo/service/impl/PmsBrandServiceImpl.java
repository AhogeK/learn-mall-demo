package com.example.learnmalldemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.entity.PmsBrand;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.form.PmsBrandForm;
import com.example.learnmalldemo.mapper.PmsBrandMapper;
import com.example.learnmalldemo.service.PmsBrandService;
import com.example.learnmalldemo.utils.BeanCopyUtil;
import com.example.learnmalldemo.vo.PmsBrandVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌管理服务层实现类
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-31 17:09
 * @since 1.00
 */
@Service
@Slf4j
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public List<PmsBrandVo> listAllBrand() {
        return BeanCopyUtil.createCopyBeanPropCollection(list(), PmsBrandVo.class);
    }

    @Override
    public void createBrand(PmsBrandForm brand) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brand, pmsBrand);
        if (!save(pmsBrand)) {
            log.debug("createBrand failed:{}", pmsBrand);
            throw new MallException(ResultCode.INSERT_FAILED);
        }
        log.debug("createBrand success:{}", pmsBrand);
    }

    @Override
    public void updateBrand(Long id, PmsBrandForm brand) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brand, pmsBrand);
        pmsBrand.setId(id);
        if (!updateById(pmsBrand)) {
            log.debug("updateBrand failed:{}", pmsBrand);
            throw new MallException(ResultCode.UPDATE_FAILED);
        }
        log.debug("updateBrand success:{}", pmsBrand);
    }

    // @Override
    // public int deleteBrand(Long id) {
    //     return brandMapper.deleteByPrimaryKey(id);
    // }
    //
    // @Override
    // public List<PmsBrand> listBrand(int pageNum, int pageSize) {
    //     PageHelper.startPage(pageNum, pageSize);
    //     return brandMapper.selectByExample(new PmsBrandExample());
    // }
    //
    // @Override
    // public PmsBrand getBrand(Long id) {
    //     return brandMapper.selectByPrimaryKey(id);
    // }
}
