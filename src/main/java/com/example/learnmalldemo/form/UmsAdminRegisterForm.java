package com.example.learnmalldemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册表单实体
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-21 16:12
 */
@Data
@ApiModel("用户注册表单实体")
public class UmsAdminRegisterForm {

    @ApiModelProperty(value = "用户名", example = "AhogeK")
    @NotBlank(message = "{notnull}")
    private String username;

    @ApiModelProperty(value = "密码", example = "123456")
    @NotBlank(message = "{notnull}")
    private String password;

    @ApiModelProperty(value = "头像", example = "https://twitter.com/szmallow_xx/status/1407474494642421761/photo/1")
    private String icon;

    @ApiModelProperty(value = "邮箱", example = "ahogek@gmail.com")
    private String email;

    @ApiModelProperty(value = "昵称", example = "AhogeK")
    private String nickName;

    @ApiModelProperty(value = "备注信息", example = "example")
    private String note;
}
