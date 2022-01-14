package com.example.learnmalldemo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表详细信息VO
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-06-21 16:16
 */
@Data
@Schema(description = "用户表详细信息VO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UmsAdminDetailVo {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "头像")
    private String icon;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "备注信息")
    private String note;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginTime;

    @Schema(description = "帐号启用状态: 0 -> 禁用; 1 -> 启用")
    private Integer status;
}
