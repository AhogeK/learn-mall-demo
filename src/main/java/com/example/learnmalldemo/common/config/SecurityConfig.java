package com.example.learnmalldemo.common.config;

import cn.hutool.json.JSONUtil;
import com.example.learnmalldemo.common.api.CommonResult;
import com.example.learnmalldemo.common.filter.JwtAuthenticationTokenFilter;
import com.example.learnmalldemo.service.admin.IUmsAdminService;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    private static final String[] GET_ANT_MATCHERS = {"/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js",
            "/swagger-resources/**", "/v2/api-docs/**", "/swagger-ui/**", "/v3/api-docs/**"};

    private final IUmsAdminService adminService;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    public SecurityConfig(@Lazy IUmsAdminService adminService,
                          JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.adminService = adminService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置网络安全
        http
                // 开启跨资源共享
                .cors().and()
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
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler((request, response, ex) -> flushResponse(response))
                .authenticationEntryPoint((request, response, ex) -> flushResponse(response));
        // 添加 JWT 拦截
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private void flushResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized()));
        response.getWriter().flush();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置认证管理
        auth.userDetailsService(username -> adminService
                .getAdminByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("用户: %s, 未找到", username)
                        )
                )).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Used by spring security if CORS is enabled.
     *
     * @return CORS拦截器
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
