package com.example.learnmalldemo.service.impl;

import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.form.PmsBrandForm;
import com.example.learnmalldemo.mbg.mapper.PmsBrandMapper;
import com.example.learnmalldemo.mbg.model.PmsBrand;
import com.example.learnmalldemo.mbg.model.PmsBrandExample;
import com.example.learnmalldemo.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
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
public class PmsBrandServiceImpl implements PmsBrandService {

    private final PmsBrandMapper brandMapper;

    public PmsBrandServiceImpl(PmsBrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand createBrand(PmsBrandForm brand) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brand, pmsBrand);
        if (brandMapper.insertSelective(pmsBrand) != 1) {
            log.debug("createBrand failed:{}", pmsBrand);
            throw new MallException(ResultCode.INSERT_FAILED);
        }
        log.debug("createBrand success:{}", pmsBrand);
        return pmsBrand;
    }

    @Override
    public PmsBrand updateBrand(Long id, PmsBrandForm brand) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brand, pmsBrand);
        pmsBrand.setId(id);
        if (brandMapper.updateByPrimaryKeySelective(pmsBrand) != 1) {
            log.debug("updateBrand failed:{}", pmsBrand);
            throw new MallException(ResultCode.UPDATE_FAILED);
        }
        log.debug("updateBrand success:{}", pmsBrand);
        return pmsBrand;
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
