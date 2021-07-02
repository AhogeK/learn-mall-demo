package com.example.learnmalldemo.common.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

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
@EnableOpenApi
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.learnmalldemo.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContexts()))
                .additionalModels(typeResolver.resolve(Void.class));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", HttpHeaders.AUTHORIZATION, SecurityScheme.In.HEADER.name());
    }

    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                        .securityReferences(Collections.singletonList(defaultAuth()))
                        .operationSelector(operationContext -> PathSelectors.any()
                                .test(operationContext.requestMappingPattern()))
                        .build();
    }

    private SecurityReference defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return new SecurityReference("Bearer", authorizationScopes);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("商城架构demo")
                .description("mall-demo")
                .contact(new Contact("AhogeK", "https://github.com/AhogeK", "ahogek@gmail.com"))
                .version("1.0")
                .build();
    }
}
