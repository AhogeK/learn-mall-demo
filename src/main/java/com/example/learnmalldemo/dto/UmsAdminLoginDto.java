package com.example.learnmalldemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 后台用户登录表单
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-02 16:21
 */
@Data
@Schema(description = "后台用户登录表单")
public class UmsAdminLoginDto {

    @NotBlank(message = "{notnull}")
    @Schema(description = "用户名", required = true, example = "AhogeK")
    private String username;

    @NotBlank(message = "{notnull}")
    @Schema(description = "密码", required = true, example = "123456")
    private String password;
}
