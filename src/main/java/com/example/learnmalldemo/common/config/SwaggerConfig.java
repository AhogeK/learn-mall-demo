package com.example.learnmalldemo.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Swagger API 文档配置
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-01 15:20
 * @since 1.00
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi createRestApi() {
        return GroupedOpenApi.builder()
                .group("demo-api")
                .pathsToMatch("/**/**")
                .packagesToScan("com.example.learnmalldemo.controller")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("商城架构demo")
                        .description("自主商城项目底层框架学习")
                        .version("v0.0.1")
                        .license(new License().name("GPL-3.0").url("https://github.com/AhogeK/learn-mall-demo/blob/master/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github地址")
                        .url("https://github.com/AhogeK/learn-mall-demo"))
                .components(new Components().addSecuritySchemes("mall-key", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));
    }
}
