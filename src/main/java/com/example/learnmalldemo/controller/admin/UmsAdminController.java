package com.example.learnmalldemo.controller.admin;

import com.example.learnmalldemo.common.annotation.LoginUser;
import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.dto.admin.UmsAdminLoginDto;
import com.example.learnmalldemo.dto.admin.UmsAdminRegisterDto;
import com.example.learnmalldemo.entity.admin.UmsAdmin;
import com.example.learnmalldemo.entity.admin.UmsPermission;
import com.example.learnmalldemo.service.admin.IUmsAdminService;
import com.example.learnmalldemo.vo.LoginVo;
import com.example.learnmalldemo.vo.UmsAdminDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 后台用户管理
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-23 13:54
 */
@RestController
@Tag(name = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    private final IUmsAdminService umsAdminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public UmsAdminController(@Lazy IUmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdminDetailVo> register(@RequestBody @Valid UmsAdminRegisterDto umsAdminRegisterDto) {
        return CommonResult.success(umsAdminService.register(umsAdminRegisterDto));
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public CommonResult<LoginVo> login(@RequestBody @Valid UmsAdminLoginDto umsAdminLoginDto) {
        String token = umsAdminService.login(umsAdminLoginDto.getUsername(), umsAdminLoginDto.getPassword());
        LoginVo result = new LoginVo();
        result.setToken(token);
        result.setTokenHead(tokenHead);
        return CommonResult.success(result);
    }

    @Operation(security = {@SecurityRequirement(name = "mall-key")}, summary = "获取用户权限")
    @GetMapping("/permission")
    public CommonResult<List<UmsPermission>> getPermissionList(@Parameter(hidden = true) @LoginUser UmsAdmin umsAdmin) {
        return CommonResult.success(umsAdminService.getPermissionList(umsAdmin.getId()));
    }
}
