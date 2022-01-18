package com.example.learnmalldemo.service.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.learnmalldemo.dto.admin.PmsBrandDto;
import com.example.learnmalldemo.dto.admin.UpdateFactoryStatusDto;
import com.example.learnmalldemo.dto.admin.UpdateShowStatusDto;
import com.example.learnmalldemo.entity.PmsBrand;
import com.example.learnmalldemo.service.pms.IPmsBrandService;
import com.example.learnmalldemo.vo.PmsBrandVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品品牌单元测试方法
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2022-01-18 15:06
 */
@Slf4j
public class PmsBrandTestService {

    private final IPmsBrandService pmsBrandService;
    private final String BRAND_NAME = "Test_" + LocalDateTime.now();
    private final PmsBrandDto testBrandDto;
    private PmsBrand testBrand = null;

    {
        testBrandDto = new PmsBrandDto();
        testBrandDto.setName(BRAND_NAME);
        testBrandDto.setBrandStory("Just Test");
    }

    public PmsBrandTestService(IPmsBrandService pmsBrandService) {
        this.pmsBrandService = pmsBrandService;
    }

    public void listAllBrandTest() {
        Assertions.assertDoesNotThrow(pmsBrandService::listAllBrand);
    }

    public void createBrandTest() {
        Assertions.assertDoesNotThrow(() -> pmsBrandService.createBrand(testBrandDto));
        testBrand = pmsBrandService.getOne(Wrappers.<PmsBrand>lambdaQuery().eq(PmsBrand::getName, BRAND_NAME));
        Assertions.assertNotNull(testBrand);
    }

    public void updateBrandTest() {
        testBrandDto.setName("Test_" + LocalDateTime.now());
        Assertions.assertDoesNotThrow(() -> pmsBrandService.updateBrand(testBrand.getId(), testBrandDto));
        Assertions.assertNull(pmsBrandService.getOne(Wrappers.<PmsBrand>lambdaQuery().eq(PmsBrand::getName, BRAND_NAME)));
    }

    public void deleteBrandTest() {
        Assertions.assertDoesNotThrow(() -> pmsBrandService.deleteBrand(testBrand.getId()));
    }

    public void getBrandTest() {
        Assertions.assertNotNull(pmsBrandService.getBrand(testBrand.getId()));
    }

    public void getBrandPageTest() {
        testBrandDto.setName(BRAND_NAME);
        createBrandTest();
        Page<PmsBrand> page = new Page<>(1, 3);
        IPage<PmsBrandVo> brandPage = pmsBrandService.getBrandPage(page, BRAND_NAME);
        Assertions.assertEquals(1, brandPage.getTotal());
        deleteBatchTest();
    }

    public void deleteBatchTest() {
        testBrandDto.setName(BRAND_NAME);
        Assertions.assertDoesNotThrow(() -> pmsBrandService.createBrand(testBrandDto));
        Assertions.assertDoesNotThrow(() -> pmsBrandService.createBrand(testBrandDto));
        Assertions.assertDoesNotThrow(() -> pmsBrandService.createBrand(testBrandDto));
        List<PmsBrand> list = pmsBrandService.list(Wrappers.<PmsBrand>lambdaQuery().eq(PmsBrand::getName, BRAND_NAME));
        Assertions.assertDoesNotThrow(() -> pmsBrandService.deleteBatch(list.stream().map(PmsBrand::getId)
                .collect(Collectors.toList())));
    }

    public void updateFactoryStatusTest() {
        UpdateFactoryStatusDto updateFactoryStatusDto = new UpdateFactoryStatusDto();
        updateFactoryStatusDto.setIds(Collections.singletonList(testBrand.getId()));
        updateFactoryStatusDto.setFactoryStatus(1);
        pmsBrandService.updateFactoryStatus(updateFactoryStatusDto);
        Assertions.assertEquals(1, pmsBrandService.getById(testBrand.getId()).getFactoryStatus());
    }

    public void updateShowStatusTest() {
        UpdateShowStatusDto updateShowStatusDto = new UpdateShowStatusDto();
        updateShowStatusDto.setIds(Collections.singletonList(testBrand.getId()));
        updateShowStatusDto.setShowStatus(1);
        pmsBrandService.updateShowStatus(updateShowStatusDto);
        Assertions.assertEquals(1, pmsBrandService.getById(testBrand.getId()).getShowStatus());
    }
}
