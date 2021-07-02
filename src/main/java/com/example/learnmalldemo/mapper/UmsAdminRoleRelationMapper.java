package com.example.learnmalldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.learnmalldemo.entity.UmsAdminRoleRelation;
import com.example.learnmalldemo.entity.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author AhogeK
 * @since 2021-06-21
 */
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {

    /**
     * 获取用户所有权限
     *
     * @param adminId 用户id
     * @return 用户权限列表
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}