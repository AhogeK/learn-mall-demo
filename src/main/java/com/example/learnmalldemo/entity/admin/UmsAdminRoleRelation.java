package com.example.learnmalldemo.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户和角色关系表
 * </p>
 *
 * @author AhogeK
 * @since 2021-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "后台用户和角色关系表")
public class UmsAdminRoleRelation {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "后台用户表id")
    private Long adminId;

    @Schema(description = "角色表id")
    private Long roleId;
}
