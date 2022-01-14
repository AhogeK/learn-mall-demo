package com.example.learnmalldemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 后台用户表实体
 *
 * @author AhogeK ahogek@gmail.com
 * @since 1.00 2021-06-08 14:07
 */
@Data
@Schema(description = "后台用户表实体")
public class UmsAdmin {

    @Schema(description = "后台用户表id")
    @TableId(type = IdType.AUTO)
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

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginTime;

    @Schema(description = "帐号启用状态: 0 -> 禁用; 1 -> 启用")
    private Integer status;
}
