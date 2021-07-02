package com.example.learnmalldemo.common.handler;

import cn.hutool.json.JSONUtil;
import com.example.learnmalldemo.common.api.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当接口没有权限时，自定义的返回结果
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-21 21:37
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(accessDeniedException.getMessage())));
        response.getWriter().flush();
    }
}
