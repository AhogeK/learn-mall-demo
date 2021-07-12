package com.example.learnmalldemo.service;

import com.example.learnmalldemo.form.VerifyAuthCodeForm;

/**
 * <p>
 * 会员登录注册管理服务层
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-11 16:21
 * @since 1.00
 */
public interface IUmsMemberService {

    /**
     * 生成验证码
     *
     * @param telephone 用户手机号
     * @return 验证码
     */
    String getAuthCode(String telephone);

    /**
     * 校验验证码
     *
     * @param verifyAuthCodeForm 校验验证码请求表单
     */
    void verifyAuthCode(VerifyAuthCodeForm verifyAuthCodeForm);
}
