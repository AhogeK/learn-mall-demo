package com.example.learnmalldemo.controller;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.form.PmsBrandAddForm;
import com.example.learnmalldemo.mbg.model.PmsBrand;
import com.example.learnmalldemo.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌管理 Controller
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-25 22:28
 * @since 1.00
 */
@Api(tags = "商品品牌管理", value = "PmsBrandController")
@Log4j2
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    private final PmsBrandService pmsBrandService;

    public PmsBrandController(PmsBrandService pmsBrandService) {
        this.pmsBrandService = pmsBrandService;
    }

    /**
     * 获取所有品牌列表
     *
     * @return 所有品牌列表
     * @author AhogeK ahgoek@gmail.com
     * @date 2021-04-14 21:37
     */
    @ApiOperation("获取所有品牌列表")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    /**
     * 添加品牌
     *
     * @param pmsBrandForm 品牌实体
     * @return 新增成功的品牌实体
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-14 21:38
     */
    @ApiOperation("添加品牌")
    @PostMapping("/create")
    public CommonResult<PmsBrand> createBrand(@RequestBody PmsBrandAddForm pmsBrandForm) {
        return CommonResult.success(pmsBrandService.createBrand(pmsBrandForm));
    }

    // @ApiOperation("更新制定id品牌信息")
    // @PostMapping("/update/{id}")
    // @ResponseBody
    // public CommonResult<PmsBrand> updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
    //     CommonResult<PmsBrand> commonResult;
    //     int count = pmsBrandService.updateBrand(id, pmsBrandDto);
    //     if (count == 1) {
    //         commonResult = CommonResult.success(pmsBrandDto);
    //         log.debug("updateBrand success:{}", pmsBrandDto);
    //     } else {
    //         commonResult = CommonResult.failed("操作失败");
    //         log.debug("updateBrand failed:{}", pmsBrandDto);
    //     }
    //     return commonResult;
    // }
    //
    // @ApiOperation("删除制定id的品牌")
    // @GetMapping("/delete/{id}")
    // @ResponseBody
    // public CommonResult<Void> deleteBrand(@PathVariable("id") Long id) {
    //     int count = pmsBrandService.deleteBrand(id);
    //     if (count == 1) {
    //         log.debug("deleteBrand success :id={}", id);
    //         return CommonResult.success(null);
    //     } else {
    //         log.debug("deleteBrand failed :id={}", id);
    //         return CommonResult.failed("操作失败");
    //     }
    // }
    //
    // @ApiOperation("分页查询品牌列表")
    // @GetMapping("/list")
    // @ResponseBody
    // @ApiImplicitParams({
    //         @ApiImplicitParam(name = "pageNum", value = "分页当前页", defaultValue = "1", dataType = "integer",
    //                 paramType = "query"),
    //         @ApiImplicitParam(name = "pageSize", value = "分页每页条数", defaultValue = "3", dataType = "integer",
    //                 paramType = "query")
    // })
    // public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
    //                                                     @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
    //     List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum, pageSize);
    //     return CommonResult.success(CommonPage.restPage(brandList));
    // }
    //
    // @ApiOperation("获取指定id的品牌详情")
    // @GetMapping("/{id}")
    // @ResponseBody
    // public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
    //     return CommonResult.success(pmsBrandService.getBrand(id));
    // }
}
