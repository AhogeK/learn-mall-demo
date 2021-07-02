package com.example.learnmalldemo.controller;

import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.form.UmsAdminLoginForm;
import com.example.learnmalldemo.form.UmsAdminRegisterForm;
import com.example.learnmalldemo.service.UmsAdminService;
import com.example.learnmalldemo.vo.UmsAdminDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-23 13:54
 */
@RestController
@Api(tags = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    private final UmsAdminService umsAdminService;

    public UmsAdminController(UmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value ="用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdminDetailVo> register(@RequestBody @Valid UmsAdminRegisterForm umsAdminRegisterForm) {
        return CommonResult.success(umsAdminService.register(umsAdminRegisterForm));
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult<Map<String, String>> login(@RequestBody @Valid UmsAdminLoginForm umsAdminLoginForm) {
        String token = umsAdminService.login(umsAdminLoginForm.getUsername(), umsAdminLoginForm.getPassword());
        Map<String, String> tokenMap = new HashMap<>(8);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
