package com.example.learnmalldemo.common.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.learnmalldemo.common.util.JwtTokenUtils;
import com.example.learnmalldemo.service.UmsAdminService;
import com.example.learnmalldemo.vo.AdminUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * JWT登录授权过滤器
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-11 16:06
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public JwtAuthenticationTokenFilter(@Lazy UmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader(this.tokenHeader);
        if (StringUtils.isBlank(header) || !header.startsWith(this.tokenHead)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 获取token并校验
        final String token = header.split(" ")[1].trim();
        if (!JwtTokenUtils.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        AdminUserDetails adminUserDetails = umsAdminService
                .getAdminByUsername(JwtTokenUtils.getUserNameFromToken(token)).orElse(null);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminUserDetails,
                null, adminUserDetails == null ? Collections.emptyList() : adminUserDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
