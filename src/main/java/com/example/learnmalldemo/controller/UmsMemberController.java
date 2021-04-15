package com.example.learnmalldemo.controller;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.form.VerifyAuthCodeForm;
import com.example.learnmalldemo.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
@Validated
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
    @GetMapping("/get-auth-code")
    @ApiImplicitParam(name = "telephone", value = "用户手机号", dataTypeClass = String.class, paramType = "query")
    public CommonResult<String> getAuthCode(@NotNull(message = "{notnull}") String telephone) {
        return CommonResult.success(umsMemberService.getAuthCode(telephone));
    }

    /**
     * 校验验证码
     *
     * @param verifyAuthCodeForm 校验验证码请求表单
     * @return 验证是否成功
     */
    @ApiOperation("校验验证码")
    @PostMapping("/verify-auth-code")
    public CommonResult<Void> verifyAuthCode(@RequestBody @Valid VerifyAuthCodeForm verifyAuthCodeForm) {
        umsMemberService.verifyAuthCode(verifyAuthCodeForm);
        return CommonResult.success();
    }
}
