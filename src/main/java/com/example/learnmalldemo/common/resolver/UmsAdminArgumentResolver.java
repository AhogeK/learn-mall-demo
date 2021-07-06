package com.example.learnmalldemo.common.resolver;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.learnmalldemo.common.annotation.LoginUser;
import com.example.learnmalldemo.common.util.JwtTokenUtils;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户参数解析器
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-05 11:44
 */
@Component
public class UmsAdminArgumentResolver implements HandlerMethodArgumentResolver {

    private final UmsAdminService umsAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public UmsAdminArgumentResolver(UmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request != null) {
            String authHeader = request.getHeader(tokenHeader);
            if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
                // 截取 Bearer 之后的字符串
                String authToken = authHeader.substring(this.tokenHead.length());
                String username = JwtTokenUtils.getUserNameFromToken(authToken);
                if (StringUtils.isNotBlank(username)) {
                    return umsAdminService.getOne(Wrappers.<UmsAdmin>lambdaQuery().eq(UmsAdmin::getUsername, username));
                }
            }
        }
        return null;
    }
}
