package com.example.learnmalldemo.service.impl;

import com.example.learnmalldemo.service.RedisService;
import com.example.learnmalldemo.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 * 会员登录注册管理服务层实现类
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-11 16:24
 * @since 1.00
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    private final RedisService redisService;

    public UmsMemberServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Value("${spring.redis.key.prefix.authCode}")
    private String redisKeyPrefixAuthCode;

    @Value("${spring.redis.key.expire.authCode}")
    private Long redisKeyExpireAuthCode;

    @Override
    public String getAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(redisKeyPrefixAuthCode + telephone, sb.toString());
        redisService.expire(redisKeyPrefixAuthCode + telephone, redisKeyExpireAuthCode);
        return sb.toString();
    }
}
