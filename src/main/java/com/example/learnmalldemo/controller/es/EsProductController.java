package com.example.learnmalldemo.controller.es;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.entity.es.EsProduct;
import com.example.learnmalldemo.service.es.IEsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "搜索商品管理", value = "EsProductController")
@Validated
@RestController
@RequestMapping("/es-products")
public class EsProductController {

    private final IEsProductService esProductService;

    public EsProductController(IEsProductService esProductService) {
        this.esProductService = esProductService;
    }

    @ApiOperation("简单搜索")
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataTypeClass = Integer.class, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataTypeClass = Integer.class, paramType = "query", example = "5")
    })
    public CommonResult<Page<EsProduct>> search(String keyword, @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize) {
        return CommonResult.success(esProductService.search(keyword, pageNum - 1, pageSize));
    }

    @ApiOperation("导入数据库数据至ES")
    @PostMapping("/import")
    public CommonResult<Integer> importAllList() {
        return CommonResult.success(esProductService.importAllList());
    }

    @ApiOperation("根据id删除商品")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", dataTypeClass = Long.class, paramType = "path", example = "0", required = true)
    public CommonResult<Void> delete(@PathVariable @NotNull(message = "{notnull}") Long id) {
        esProductService.delete(id);
        return CommonResult.success();
    }

    @ApiOperation("批量删除商品")
    @DeleteMapping
    @ApiImplicitParam(name = "ids", value = "id数组", dataType = "array", dataTypeClass = Long[].class, paramType = "query")
    public CommonResult<Void> delete(Long[] ids) {
        esProductService.delete(ids);
        return CommonResult.success();
    }

    @ApiOperation("根据id创建商品")
    @PostMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "要创建的商品id", dataTypeClass = Long.class, paramType = "path",
            example = "1", required = true)
    public CommonResult<Void> create(@PathVariable @NotNull(message = "{notnull}") Long id) {
        esProductService.create(id);
        return CommonResult.success();
    }
}
