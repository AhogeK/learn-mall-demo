package com.example.learnmalldemo.vo;

import com.example.learnmalldemo.entity.admin.UmsAdmin;
import com.example.learnmalldemo.entity.admin.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity 用户详细信息
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-22 10:22
 */
public class AdminUserDetails implements UserDetails {

    private final UmsAdmin umsAdmin;

    private final List<UmsPermission> permissionList;

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户权限
        return permissionList.stream().filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
