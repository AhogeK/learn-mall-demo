package com.example.learnmalldemo.controller.mongo;

import com.example.learnmalldemo.common.annotation.LoginUser;
import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.entity.mongo.MemberReadHistory;
import com.example.learnmalldemo.form.MemberReadHistoryAddForm;
import com.example.learnmalldemo.service.mongo.IMemberReadHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "会员商品浏览记录管理", description = "MemberReadHistoryController")
public class MemberReadHistoryController {

    private final IMemberReadHistoryService memberReadHistoryService;

    public MemberReadHistoryController(IMemberReadHistoryService memberReadHistoryService) {
        this.memberReadHistoryService = memberReadHistoryService;
    }

    @Operation(summary = "创建浏览记录")
    @PostMapping
    public CommonResult<Void> add(@RequestBody @Valid MemberReadHistoryAddForm memberReadHistoryAddForm,
                                  @Parameter(hidden = true) @LoginUser UmsAdmin umsAdmin) {
        memberReadHistoryService.add(memberReadHistoryAddForm, umsAdmin);
        return CommonResult.success();
    }

    @Operation(summary = "批量删除浏览记录")
    @DeleteMapping
    @Parameter(name = "ids", description = "id数组", in = ParameterIn.QUERY, required = true, example = "1")
    public CommonResult<Void> removeBatchById(@RequestParam("ids") @Size(min = 1, message = "{not-zero}")
                                              @NotNull(message = "{notnull}") String[] ids) {
        memberReadHistoryService.removeBatchById(ids);
        return CommonResult.success();
    }

    @Operation(summary = "简单搜索")
    @GetMapping
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页", in = ParameterIn.QUERY, example = "1"),
            @Parameter(name = "pageSize", description = "每页大小", in = ParameterIn.QUERY, example = "5")
    })
    public CommonResult<Page<MemberReadHistory>> page(@Parameter(hidden = true) @LoginUser UmsAdmin umsAdmin, @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "5") Integer pageSize) {
        return CommonResult.success(memberReadHistoryService.page(umsAdmin.getId(), pageNum - 1, pageSize));
    }
}
