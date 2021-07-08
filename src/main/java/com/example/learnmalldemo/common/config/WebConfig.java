package com.example.learnmalldemo.common.config;

import com.example.learnmalldemo.common.resolver.UmsAdminArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * web配置
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-07 09:33
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UmsAdminArgumentResolver umsAdminArgumentResolver;

    public WebConfig(UmsAdminArgumentResolver umsAdminArgumentResolver) {
        this.umsAdminArgumentResolver = umsAdminArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(umsAdminArgumentResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }
}
