package com.example.learnmalldemo.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.learnmalldemo.dto.UmsAdminRegisterDto;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.entity.UmsPermission;
import com.example.learnmalldemo.vo.AdminUserDetails;
import com.example.learnmalldemo.vo.UmsAdminDetailVo;

import java.util.List;
import java.util.Optional;

/**
 * 后台管理Service
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-05-26 16:14
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    /**
     * 根据用户名获取后台管理员
     *
     * @param name 用户名
     * @return 后台管理员实体
     */
    Optional<AdminUserDetails> getAdminByUsername(String name);

    /**
     * 注册功能
     *
     * @param umsAdminRegisterDto 用户注册表单实体
     * @return 注册用户详细信息
     */
    UmsAdminDetailVo register(UmsAdminRegisterDto umsAdminRegisterDto);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限(包括角色权限和+-权限)
     *
     * @param adminId 用户id
     * @return 权限列表
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
