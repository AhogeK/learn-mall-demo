package com.example.learnmalldemo.common.handler;

import cn.hutool.json.JSONUtil;
import com.example.learnmalldemo.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义返回结果
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-22 10:11
 */
@Component
public class RestAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
