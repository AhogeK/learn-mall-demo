package com.example.learnmalldemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.entity.PmsBrand;
import com.example.learnmalldemo.form.PmsBrandForm;
import com.example.learnmalldemo.service.PmsBrandService;
import com.example.learnmalldemo.vo.PmsBrandVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
@RequestMapping("/brands")
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
    @PreAuthorize("hasAuthority('not')")
    @ApiOperation("获取所有品牌列表")
    @GetMapping
    public CommonResult<List<PmsBrandVo>> getBrandList() {
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
    @PreAuthorize("hasAuthority('pms:brand:create')")
    @ApiOperation("添加品牌")
    @PostMapping
    public CommonResult<Void> createBrand(@RequestBody @Valid PmsBrandForm pmsBrandForm) {
        pmsBrandService.createBrand(pmsBrandForm);
        return CommonResult.success();
    }

    /**
     * 更新品牌
     *
     * @param id        品牌id
     * @param brandForm 更新表单
     * @return 请求是否成功
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-15 18:08
     */
    @PreAuthorize("hasAuthority('pms:brand:update')")
    @ApiOperation("更新制定id品牌信息")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataTypeClass = String.class, paramType = "path")
    public CommonResult<Void> updateBrand(@PathVariable("id") @NotNull(message = "{notnull}") Long id,
                                          @RequestBody @Valid PmsBrandForm brandForm) {
        pmsBrandService.updateBrand(id, brandForm);
        return CommonResult.success();
    }

    /**
     * 删除品牌
     *
     * @param id 品牌ID
     * @return 接口请求是否成功
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-15 22:29
     */
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @ApiOperation("删除制定id的品牌")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "品牌id", required = true, dataTypeClass = String.class, paramType = "path")
    public CommonResult<Void> deleteBrand(@PathVariable("id") @NotNull(message = "{notnull}") Long id) {
        pmsBrandService.deleteBrand(id);
        return CommonResult.success();
    }

    /**
     * 分页查询品牌列表
     *
     * @param current 分页当前页
     * @param size    分页每页条数
     * @return 品牌分页列表
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-15 22:30
     */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("分页查询品牌列表")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "分页当前页", dataType = "integer",
                    dataTypeClass = Integer.class, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "分页每页条数", dataType = "integer",
                    dataTypeClass = Integer.class, paramType = "query", example = "3")
    })
    public CommonResult<IPage<PmsBrandVo>> listBrand(@RequestParam(defaultValue = "1") Integer current,
                                                     @RequestParam(defaultValue = "3") Integer size) {
        return CommonResult.success(pmsBrandService.getBrandPage(new Page<>(current, size)));
    }

    /**
     * 根据制定id获取品牌详情
     *
     * @param id 品牌id
     * @return 品牌详细信息
     */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("获取指定id的品牌详情")
    @GetMapping("/{id}")
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") @NotNull(message = "{notnull}") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
