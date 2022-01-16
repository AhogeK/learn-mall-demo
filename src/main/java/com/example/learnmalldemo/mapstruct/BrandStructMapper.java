package com.example.learnmalldemo.mapstruct;

import com.example.learnmalldemo.entity.PmsBrand;
import com.example.learnmalldemo.vo.PmsBrandVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * brand map struct
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-01-16 18:15
 */
@Mapper
public interface BrandStructMapper {

    BrandStructMapper INSTANCE = Mappers.getMapper(BrandStructMapper.class);

    /**
     * {@link PmsBrand} to {@link PmsBrandVo}
     *
     * @param pmsBrand source
     * @return {@link PmsBrandVo}
     */
    PmsBrandVo pmsBrandToPmsBrandVO(PmsBrand pmsBrand);

    /**
     * 品牌列表转换
     *
     * @param pmsBrands {@link PmsBrand}列表
     * @return {@link PmsBrandVo}列表
     */
    List<PmsBrandVo> pmsBrandListToPmsBrandVoList(List<PmsBrand> pmsBrands);
}
