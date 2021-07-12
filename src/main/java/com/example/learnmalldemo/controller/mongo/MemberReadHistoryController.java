package com.example.learnmalldemo.controller.mongo;

import com.example.learnmalldemo.common.annotation.LoginUser;
import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.entity.mongo.MemberReadHistory;
import com.example.learnmalldemo.form.mongo.MemberReadHistoryAddForm;
import com.example.learnmalldemo.service.mongo.IMemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 会员商品浏览记录管理 Controller
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 11:03
 */
@Validated
@RestController
@RequestMapping("/member/read-history")
@Api(tags = "会员商品浏览记录管理", value = "MemberReadHistoryController")
public class MemberReadHistoryController {

    private final IMemberReadHistoryService memberReadHistoryService;

    public MemberReadHistoryController(IMemberReadHistoryService memberReadHistoryService) {
        this.memberReadHistoryService = memberReadHistoryService;
    }

    @ApiOperation("创建浏览记录")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid MemberReadHistoryAddForm memberReadHistoryAddForm,
                                  @ApiIgnore @LoginUser UmsAdmin umsAdmin) {
        memberReadHistoryService.add(memberReadHistoryAddForm, umsAdmin);
        return CommonResult.success();
    }

    @ApiOperation("批量删除浏览记录")
    @DeleteMapping
    @ApiImplicitParam(name = "ids", value = "id数组", dataType = "array", dataTypeClass = String[].class,
            paramType = "query", required = true, example = "1")
    public CommonResult<Void> removeBatchById(@RequestParam("ids") @Size(min = 1, message = "{not-zero}")
                                              @NotNull(message = "{notnull}") String[] ids) {
        memberReadHistoryService.removeBatchById(ids);
        return CommonResult.success();
    }

    @ApiOperation("简单搜索")
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataTypeClass = Integer.class, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataTypeClass = Integer.class, paramType = "query", example = "5")
    })
    public CommonResult<Page<MemberReadHistory>> page(@ApiIgnore @LoginUser UmsAdmin umsAdmin, @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "5") Integer pageSize) {
        return CommonResult.success(memberReadHistoryService.page(umsAdmin.getId(), pageNum - 1, pageSize));
    }
}
