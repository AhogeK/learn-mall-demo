package com.example.learnmalldemo.service.admin.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.common.util.JwtTokenUtils;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.entity.UmsPermission;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.form.UmsAdminRegisterForm;
import com.example.learnmalldemo.mapper.UmsAdminMapper;
import com.example.learnmalldemo.mapper.UmsAdminRoleRelationMapper;
import com.example.learnmalldemo.service.admin.IUmsAdminService;
import com.example.learnmalldemo.vo.AdminUserDetails;
import com.example.learnmalldemo.vo.UmsAdminDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 后台管理Service层实现类
 *
 * @author AhogeK ahogek@gmail.com
 * @since 1.00 | 2021-06-08 14:18
 */
@Slf4j
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService,
        UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    public UmsAdminServiceImpl(PasswordEncoder passwordEncoder, UmsAdminRoleRelationMapper umsAdminRoleRelationMapper) {
        this.passwordEncoder = passwordEncoder;
        this.umsAdminRoleRelationMapper = umsAdminRoleRelationMapper;
    }

    @Override
    public Optional<AdminUserDetails> getAdminByUsername(String name) {
        UmsAdmin umsAdmin =
                getOne(Wrappers.<UmsAdmin>lambdaQuery().eq(StringUtils.isNotBlank(name), UmsAdmin::getUsername, name));
        if (umsAdmin != null) {
            List<UmsPermission> permissionList = getPermissionList(umsAdmin.getId());
            return Optional.of(new AdminUserDetails(umsAdmin, permissionList));
        }
        return Optional.empty();
    }

    @Override
    public UmsAdminDetailVo register(UmsAdminRegisterForm umsAdminRegisterForm) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminRegisterForm, umsAdmin);
        umsAdmin.setStatus(1);
        // 查询用户名是否重复
        if (count(Wrappers.<UmsAdmin>lambdaQuery().eq(UmsAdmin::getUsername, umsAdminRegisterForm.getUsername())) > 0) {
            throw new MallException(404001, "注册用户名已存在");
        }
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        if (!save(umsAdmin)) {
            throw new MallException(ResultCode.INSERT_FAILED);
        }
        UmsAdminDetailVo umsAdminDetailVo = new UmsAdminDetailVo();
        BeanUtils.copyProperties(umsAdmin, umsAdminDetailVo);
        return umsAdminDetailVo;
    }

    @Override
    public String login(String username, String password) {
        String token;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new MallException(404002, "登录失败，帐号或密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = JwtTokenUtils.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new MallException(404002, "登录失败，帐号或密码错误");
        }
        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationMapper.getPermissionList(adminId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAdminByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with username - %s, not found", username))
                );
    }
}
