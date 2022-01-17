package com.example.learnmalldemo.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.dto.PmsBrandDto;
import com.example.learnmalldemo.entity.PmsBrand;
import com.example.learnmalldemo.service.pms.IPmsBrandService;
import com.example.learnmalldemo.vo.PmsBrandVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
@Log4j2
@Validated
@RestController
@RequestMapping("/brands")
@SecurityRequirement(name = "mall-key")
@Tag(name = "商品品牌管理", description = "PmsBrandController")
public class PmsBrandController {

    private final IPmsBrandService pmsBrandService;

    public PmsBrandController(IPmsBrandService pmsBrandService) {
        this.pmsBrandService = pmsBrandService;
    }

    /**
     * 获取所有品牌列表
     *
     * @return 所有品牌列表
     * @author AhogeK ahgoek@gmail.com
     * @date 2021-04-14 21:37
     */
    @Operation(summary = "获取所有品牌列表")
    @GetMapping
    public CommonResult<List<PmsBrandVo>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    /**
     * 添加品牌
     *
     * @param pmsBrandDto 品牌实体
     * @return 新增成功的品牌实体
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-14 21:38
     */
    @PreAuthorize("hasAuthority('pms:brand:create')")
    @Operation(summary = "添加品牌")
    @PostMapping
    public CommonResult<Void> createBrand(@RequestBody @Valid PmsBrandDto pmsBrandDto) {
        pmsBrandService.createBrand(pmsBrandDto);
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
    @Operation(summary = "更新制定id品牌信息")
    @PutMapping("/{id}")
    @Parameter(name = "id", description = "品牌id", required = true, in = ParameterIn.PATH)
    public CommonResult<Void> updateBrand(@PathVariable("id") @NotNull(message = "{notnull}") Long id,
                                          @RequestBody @Valid PmsBrandDto brandForm) {
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
    @Operation(summary = "删除制定id的品牌")
    @DeleteMapping("/{id}")
    @Parameter(name = "id", description = "品牌id", required = true, in = ParameterIn.PATH)
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
    @Operation(summary = "分页查询品牌列表")
    @GetMapping("/page")
    @Parameter(name = "keyword", description = "品牌名称", in = ParameterIn.QUERY)
    @Parameter(name = "current", description = "分页当前页", in = ParameterIn.QUERY, example = "1")
    @Parameter(name = "size", description = "分页每页条数", in = ParameterIn.QUERY, example = "3")
    public CommonResult<IPage<PmsBrandVo>> listBrand(@RequestParam(required = false) String keyword,
                                                     @RequestParam(defaultValue = "1") Integer current,
                                                     @RequestParam(defaultValue = "3") Integer size) {
        return CommonResult.success(pmsBrandService.getBrandPage(new Page<>(current, size), keyword));
    }

    /**
     * 根据制定id获取品牌详情
     *
     * @param id 品牌id
     * @return 品牌详细信息
     */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @Operation(summary = "获取指定id的品牌详情")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") @NotNull(message = "{notnull}") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }

    /**
     * 通过id进行批量删除品牌
     *
     * @param ids 品牌id列表
     * @return 接口请求是否成功
     * @author AhogeK ahogek@gmail.com
     * @date 2021-01-17 10:36
     */
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @Operation(summary = "通过id进行批量删除品牌")
    @DeleteMapping
    @Parameter(name = "ids", description = "品牌id列表", in = ParameterIn.QUERY, example = "1, 2, 3")
    public CommonResult<Void> deleteBatch(@RequestParam(name = "ids") @NotEmpty(message = "品牌id列表不能为空") List<Long> ids) {
        pmsBrandService.deleteBatch(ids);
        return CommonResult.success();
    }
}
