package com.example.learnmalldemo.controller;

import com.example.learnmalldemo.common.annotation.LoginUser;
import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.entity.UmsPermission;
import com.example.learnmalldemo.form.UmsAdminLoginForm;
import com.example.learnmalldemo.form.UmsAdminRegisterForm;
import com.example.learnmalldemo.service.IUmsAdminService;
import com.example.learnmalldemo.vo.UmsAdminDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

    private final IUmsAdminService IUmsAdminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public UmsAdminController(@Lazy IUmsAdminService IUmsAdminService) {
        this.IUmsAdminService = IUmsAdminService;
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdminDetailVo> register(@RequestBody @Valid UmsAdminRegisterForm umsAdminRegisterForm) {
        return CommonResult.success(IUmsAdminService.register(umsAdminRegisterForm));
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult<Map<String, String>> login(@RequestBody @Valid UmsAdminLoginForm umsAdminLoginForm) {
        String token = IUmsAdminService.login(umsAdminLoginForm.getUsername(), umsAdminLoginForm.getPassword());
        Map<String, String> tokenMap = new HashMap<>(8);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户权限")
    @GetMapping("/permission")
    public CommonResult<List<UmsPermission>> getPermissionList(@ApiIgnore @LoginUser UmsAdmin umsAdmin) {
        return CommonResult.success(IUmsAdminService.getPermissionList(umsAdmin.getId()));
    }
}
