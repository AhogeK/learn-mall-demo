package com.example.learnmalldemo.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * 自定义启动信息
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-13 15:16
 * @since 1.00
 */
@Configuration
public class ApplicationContextConfig implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        printSwaggerAddress(applicationContext);
    }

    /**
     * 打印 swagger 地址
     *
     * @param ctx applicationContext
     */
    public void printSwaggerAddress(ApplicationContext ctx) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) ctx
                    .getBean("tomcatServletWebServerFactory");
            int port = tomcatServletWebServerFactory.getPort();
            String contextPath = tomcatServletWebServerFactory.getContextPath();
            System.out.println("---------启动成功, Swagger地址: http://" + host + ":" + port + contextPath + "/swagger-ui/index.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
