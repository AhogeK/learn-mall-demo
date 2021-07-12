package com.example.learnmalldemo.service.es.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.mapper.PmsProductMapper;
import com.example.learnmalldemo.nosql.es.entity.EsProduct;
import com.example.learnmalldemo.nosql.es.repository.EsProductRepository;
import com.example.learnmalldemo.service.es.IEsProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * ES 商品管理 Service 实现类
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 11:43
 */
@Service
public class EsProductServiceImpl implements IEsProductService {

    private final EsProductRepository productRepository;
    private final PmsProductMapper pmsProductMapper;

    public EsProductServiceImpl(EsProductRepository productRepository, PmsProductMapper pmsProductMapper) {
        this.productRepository = productRepository;
        this.pmsProductMapper = pmsProductMapper;
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }

    @Override
    public Integer importAllList() {
        List<EsProduct> allProduct = pmsProductMapper.getProducts(null);
        Iterable<EsProduct> esProducts = productRepository.saveAll(allProduct);
        return Math.toIntExact(StreamSupport.stream(esProducts.spliterator(), false).count());
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
            pmsProductMapper.deleteById(id);
        } catch (Exception e) {
            throw new MallException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public void delete(Long[] ids) {
        try {
            Stream.of(ids).forEach(productRepository::deleteById);
            pmsProductMapper.deleteBatchIds(Arrays.asList(ids));
        } catch (Exception e) {
            throw new MallException(ResultCode.DELETE_FAILED);
        }
    }

    @Override
    public void create(Long id) {
        List<EsProduct> products = pmsProductMapper.getProducts(id);
        if (CollectionUtils.isEmpty(products)) {
            throw new MallException(ResultCode.SELECT_FAILED);
        }
        productRepository.save(products.get(0));
    }
}
