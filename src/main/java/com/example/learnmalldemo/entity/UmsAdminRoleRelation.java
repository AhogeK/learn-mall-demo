package com.example.learnmalldemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "UmsAdminRoleRelation对象", description = "后台用户和角色关系表")
public class UmsAdminRoleRelation {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;

    private Long adminId;

    private Long roleId;
}