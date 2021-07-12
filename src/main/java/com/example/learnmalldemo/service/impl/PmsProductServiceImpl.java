package com.example.learnmalldemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.learnmalldemo.entity.PmsProduct;
import com.example.learnmalldemo.mapper.PmsProductMapper;
import com.example.learnmalldemo.service.IPmsProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author AhogeK
 * @since 2021-07-09
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

}
