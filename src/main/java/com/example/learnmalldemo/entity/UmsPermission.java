package com.example.learnmalldemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 后台用户权限表
 * </p>
 *
 * @author AhogeK
 * @since 2021-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UmsPermission对象", description = "后台用户权限表")
public class UmsPermission {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "父级权限id")
    private Long pid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;

    @ApiModelProperty(value = "前端资源路径")
    private String uri;

    @ApiModelProperty(value = "启用状态；0->禁用；1->启用")
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}