package com.example.learnmalldemo.common.resolver;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.learnmalldemo.common.annotation.LoginUser;
import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.common.util.JwtTokenUtils;
import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.service.IUmsAdminService;
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

    private final IUmsAdminService IUmsAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public UmsAdminArgumentResolver(IUmsAdminService IUmsAdminService) {
        this.IUmsAdminService = IUmsAdminService;
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
            final String header = request.getHeader(this.tokenHeader);
            if (StringUtils.isNotBlank(header) && header.startsWith(this.tokenHead)) {
                final String token = header.split(" ")[1].trim();
                final String username = JwtTokenUtils.getUserNameFromToken(token);
                if (StringUtils.isNotBlank(username)) {
                    return IUmsAdminService.getOne(Wrappers.<UmsAdmin>lambdaQuery().eq(UmsAdmin::getUsername, username));
                }
            }
        }
        throw new MallException(ResultCode.UNAUTHORIZED);
    }
}
