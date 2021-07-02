package com.example.learnmalldemo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表详细信息VO
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-21 16:16
 */
@Data
@ApiModel("用户表详细信息VO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UmsAdminDetailVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "帐号启用状态: 0 -> 禁用; 1 -> 启用")
    private Integer status;
}
