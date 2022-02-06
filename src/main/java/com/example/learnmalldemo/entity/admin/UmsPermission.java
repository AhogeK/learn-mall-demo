package com.example.learnmalldemo.entity.admin;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
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
@Schema(description = "后台用户权限表")
public class UmsPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "父级权限id")
    private Long pid;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "权限值")
    private String value;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;

    @Schema(description = "前端资源路径")
    private String uri;

    @Schema(description = "启用状态；0->禁用；1->启用")
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "排序")
    private Integer sort;
}
