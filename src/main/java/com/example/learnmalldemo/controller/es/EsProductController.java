package com.example.learnmalldemo.controller.es;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.entity.es.EsProduct;
import com.example.learnmalldemo.service.es.IEsProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * ES 商品控制层
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-09 12:17
 */
@Tag(name = "搜索商品管理", description = "EsProductController")
@Validated
@RestController
@RequestMapping("/es-products")
public class EsProductController {

    private final IEsProductService esProductService;

    public EsProductController(IEsProductService esProductService) {
        this.esProductService = esProductService;
    }

    @Operation(summary = "简单搜索")
    @GetMapping
    @Parameters({
            @Parameter(name = "keyword", description = "关键字", in = ParameterIn.QUERY),
            @Parameter(name = "pageNum", description = "当前页", in = ParameterIn.QUERY, example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", in = ParameterIn.QUERY, example = "5")
    })
    public CommonResult<Page<EsProduct>> search(String keyword, @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize) {
        return CommonResult.success(esProductService.search(keyword, pageNum - 1, pageSize));
    }

    @Operation(summary = "导入数据库数据至ES")
    @PostMapping("/import")
    public CommonResult<Integer> importAllList() {
        return CommonResult.success(esProductService.importAllList());
    }

    @Operation(summary = "根据id删除商品")
    @DeleteMapping("/{id}")
    @Parameter(name = "id", description = "商品id", in = ParameterIn.PATH, example = "0", required = true)
    public CommonResult<Void> delete(@PathVariable @NotNull(message = "{notnull}") Long id) {
        esProductService.delete(id);
        return CommonResult.success();
    }

    @Operation(summary = "批量删除商品")
    @DeleteMapping
    @Parameter(name = "ids", description = "id数组", in = ParameterIn.QUERY)
    public CommonResult<Void> delete(Long[] ids) {
        esProductService.delete(ids);
        return CommonResult.success();
    }

    @Operation(summary = "根据id创建商品")
    @PostMapping("/{id}")
    @Parameter(name = "id", description = "要创建的商品id", in = ParameterIn.PATH, example = "1", required = true)
    public CommonResult<Void> create(@PathVariable @NotNull(message = "{notnull}") Long id) {
        esProductService.create(id);
        return CommonResult.success();
    }
}
