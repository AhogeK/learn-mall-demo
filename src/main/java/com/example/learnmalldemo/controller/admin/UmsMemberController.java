package com.example.learnmalldemo.controller.admin;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.dto.VerifyAuthCodeDto;
import com.example.learnmalldemo.service.admin.IUmsMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "会员登录注册管理")
@RequestMapping("/sso")
@Validated
@SecurityRequirement(name = "mall-key")
public class UmsMemberController {

    private final IUmsMemberService umsMemberService;

    public UmsMemberController(IUmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    /**
     * 获取验证码接口
     *
     * @param telephone 用户手机号
     * @return 验证码
     */
    @Operation(summary = "获取验证码")
    @GetMapping("/get-auth-code")
    @Parameter(name = "telephone", description = "用户手机号", in = ParameterIn.PATH)
    public CommonResult<String> getAuthCode(@NotNull(message = "{notnull}") String telephone) {
        return CommonResult.success(umsMemberService.getAuthCode(telephone));
    }

    /**
     * 校验验证码
     *
     * @param verifyAuthCodeDto 校验验证码请求表单
     * @return 验证是否成功
     */
    @Operation(summary = "校验验证码")
    @PostMapping("/verify-auth-code")
    public CommonResult<Void> verifyAuthCode(@RequestBody @Valid VerifyAuthCodeDto verifyAuthCodeDto) {
        umsMemberService.verifyAuthCode(verifyAuthCodeDto);
        return CommonResult.success();
    }
}
