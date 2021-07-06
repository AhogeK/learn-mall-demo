package com.example.learnmalldemo.common.annotation;

import java.lang.annotation.*;

/**
 * 用于获取登录用户
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-05 08:08
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
