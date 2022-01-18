package com.example.learnmalldemo.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册表单实体
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-21 16:12
 */
@Data
@Schema(description = "用户注册表单实体")
public class UmsAdminRegisterDto {

    @Schema(description = "用户名", example = "AhogeK")
    @NotBlank(message = "{notnull}")
    private String username;

    @Schema(description = "密码", example = "123456")
    @NotBlank(message = "{notnull}")
    private String password;

    @Schema(description = "头像", example = "https://twitter.com/szmallow_xx/status/1407474494642421761/photo/1")
    private String icon;

    @Schema(description = "邮箱", example = "ahogek@gmail.com")
    private String email;

    @Schema(description = "昵称", example = "AhogeK")
    private String nickName;

    @Schema(description = "备注信息", example = "example")
    private String note;
}
