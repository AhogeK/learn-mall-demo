package com.example.learnmalldemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;
import javax.validation.constraints.NotBlank;

/**
 * 后台用户登录表单
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-02 16:21
 */
@Data
@ApiModel("后台用户登录表单")
public class UmsAdminLoginForm {

    @NotBlank(message = "{notnull}")
    @ApiModelProperty(value = "用户名", required = true, example = "AhogeK")
    private String username;

    @NotBlank(message = "{notnull}")
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;
}
