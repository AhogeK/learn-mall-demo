package com.example.learnmalldemo.config;

import com.example.learnmalldemo.service.UmsAdminService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * SpringSecurity的配置
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-05-26 16:10
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UmsAdminService adminService;
    private final String[] GET_ANT_MATCHERS = {"/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js",
            "/swagger-resources/**", "/v2/api-docs/**"};

    public SecurityConfig(UmsAdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 由于使用 JWT 所以禁用 CSRF
                .csrf().disable()
                // 基于 token 所以不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, GET_ANT_MATCHERS)
                .permitAll()
                // 对登录注册地址开放
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()
                // 跨域请求会先进行一次 options 请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated();
        // 无缓存
        http.headers().cacheControl();
        // 添加 JWT 拦截
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private Filter jwtAuthenticationTokenFilter() {
        return null;
    }
}
