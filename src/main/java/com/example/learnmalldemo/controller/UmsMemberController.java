package com.example.learnmalldemo.controller;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.service.UmsMemberService;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员登录注册管理控制层
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-11 16:18
 * @since 1.00
 */
@RestController
@Api(tags = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {

    private final UmsMemberService umsMemberService;

    public UmsMemberController(UmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    /**
     * 获取验证码接口
     *
     * @param telephone 用户手机号
     * @return 验证码
     */
    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    @ApiImplicitParam(name = "telephone", value = "用户手机号", dataType = "string", paramType = "query", required = true)
    public CommonResult<String> getAuthCode(@NotNull String telephone) {
        return CommonResult.success(umsMemberService.getAuthCode(telephone));
    }
}
