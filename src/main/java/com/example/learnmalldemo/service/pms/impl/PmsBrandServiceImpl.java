package com.example.learnmalldemo.service.pms.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.dto.admin.PmsBrandDto;
import com.example.learnmalldemo.dto.admin.UpdateFactoryStatusDto;
import com.example.learnmalldemo.dto.admin.UpdateShowStatusDto;
import com.example.learnmalldemo.entity.PmsBrand;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.mapper.PmsBrandMapper;
import com.example.learnmalldemo.mapstruct.BrandStructMapper;
import com.example.learnmalldemo.service.pms.IPmsBrandService;
import com.example.learnmalldemo.utils.BeanCopyUtil;
import com.example.learnmalldemo.vo.PmsBrandVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {

    @Override
    public List<PmsBrandVo> listAllBrand() {
        return BeanCopyUtil.createCopyBeanPropCollection(list(), PmsBrandVo.class);
    }

    @Override
    public void createBrand(PmsBrandDto brand) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brand, pmsBrand);
        if (!save(pmsBrand)) {
            log.debug("createBrand failed:{}", pmsBrand);
            throw new MallException(ResultCode.INSERT_FAILED);
        }
        log.debug("createBrand success:{}", pmsBrand);
    }

    @Override
    public void updateBrand(Long id, PmsBrandDto brand) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brand, pmsBrand);
        pmsBrand.setId(id);
        if (!updateById(pmsBrand)) {
            log.debug("updateBrand failed:{}", pmsBrand);
            throw new MallException(ResultCode.UPDATE_FAILED);
        }
        log.debug("updateBrand success:{}", pmsBrand);
    }

    @Override
    public void deleteBrand(Long id) {
        if (!removeById(id)) {
            log.debug("delete brand failed.");
            throw new MallException(ResultCode.DELETE_FAILED);
        }
        log.debug("delete brand success.");
    }

    @Override
    public IPage<PmsBrandVo> getBrandPage(Page<PmsBrand> pmsBrandPage, String keywords) {
        Page<PmsBrand> brandPage = page(pmsBrandPage, Wrappers.<PmsBrand>lambdaQuery()
                .like(StringUtils.isNotEmpty(keywords), PmsBrand::getName, keywords));
        IPage<PmsBrandVo> resultPage = new Page<>();
        BeanUtils.copyProperties(brandPage, resultPage);
        if (CollectionUtils.isEmpty(brandPage.getRecords())) {
            log.debug("brand has no page");
            return resultPage;
        }
        resultPage.setRecords(BrandStructMapper.INSTANCE.pmsBrandListToPmsBrandVoList(brandPage.getRecords()));
        log.debug("brand page data:{}", resultPage);
        return resultPage;
    }

    @Override
    public PmsBrand getBrand(Long id) {
        PmsBrand brand = getById(id);
        if (ObjectUtils.isEmpty(brand)) {
            log.debug("get a empty brand");
            throw new MallException(ResultCode.SELECT_FAILED);
        }
        log.debug("get a brand:{}", brand);
        return brand;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> ids) {
        if (!removeBatchByIds(ids)) {
            log.warn("delete batch brands by ids failed!");
            throw new MallException(ResultCode.DELETE_FAILED);
        }
        log.debug("delete batch brands success");
    }

    @Override
    public void updateFactoryStatus(UpdateFactoryStatusDto updateFactoryStatusDto) {
        if (!update(Wrappers.<PmsBrand>lambdaUpdate().in(PmsBrand::getId,
                updateFactoryStatusDto.getIds()).set(PmsBrand::getFactoryStatus,
                updateFactoryStatusDto.getFactoryStatus()))) {
            throw new MallException(ResultCode.UPDATE_FAILED);
        }
        log.debug("update batch brands factory status success");
    }

    @Override
    public void updateShowStatus(UpdateShowStatusDto updateShowStatusDto) {
        if (!update(Wrappers.<PmsBrand>lambdaUpdate().in(PmsBrand::getId,
                updateShowStatusDto.getIds()).set(PmsBrand::getShowStatus,
                updateShowStatusDto.getShowStatus()))) {
            throw new MallException(ResultCode.UPDATE_FAILED);
        }
        log.debug("update batch brands show status success");
    }
}