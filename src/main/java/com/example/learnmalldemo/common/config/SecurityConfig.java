package com.example.learnmalldemo.common.config;

import com.example.learnmalldemo.common.filter.JwtAuthenticationTokenFilter;
import com.example.learnmalldemo.common.handler.RestAuthenticationEntryPointHandler;
import com.example.learnmalldemo.common.handler.RestfulAccessDeniedHandler;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.entity.UmsPermission;
import com.example.learnmalldemo.service.UmsAdminService;
import com.example.learnmalldemo.vo.AdminUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

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

    private final static String[] GET_ANT_MATCHERS = {"/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js",
            "/swagger-resources/**", "/v2/api-docs/**", "/swagger-ui/**"};

    private final UmsAdminService adminService;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPointHandler restAuthenticationEntryPointHandler;

    public SecurityConfig(@Lazy UmsAdminService adminService, RestfulAccessDeniedHandler restfulAccessDeniedHandler,
                          RestAuthenticationEntryPointHandler restAuthenticationEntryPointHandler) {
        this.adminService = adminService;
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
        this.restAuthenticationEntryPointHandler = restAuthenticationEntryPointHandler;
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
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPointHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> {
            UmsAdmin admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
