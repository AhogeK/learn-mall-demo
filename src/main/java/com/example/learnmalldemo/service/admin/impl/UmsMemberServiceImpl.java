package com.example.learnmalldemo.service.admin.impl;

import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.common.constants.NumberConstants;
import com.example.learnmalldemo.dto.admin.VerifyAuthCodeDto;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.service.admin.IUmsMemberService;
import com.example.learnmalldemo.service.redis.IRedisService;
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
public class UmsMemberServiceImpl implements IUmsMemberService {

    private final IRedisService redisService;
    @Value("${spring.redis.key.prefix.authCode}")
    private String redisKeyPrefixAuthCode;
    @Value("${spring.redis.key.expire.authCode}")
    private Long redisKeyExpireAuthCode;

    public UmsMemberServiceImpl(IRedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public String getAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = NumberConstants.ZERO; i < NumberConstants.SIX; i++) {
            sb.append(random.nextInt(NumberConstants.TEN));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(redisKeyPrefixAuthCode + telephone, sb.toString());
        redisService.expire(redisKeyPrefixAuthCode + telephone, redisKeyExpireAuthCode);
        return sb.toString();
    }

    @Override
    public void verifyAuthCode(VerifyAuthCodeDto verifyAuthCodeDto) {
        String realAuthCode = redisService.get(redisKeyPrefixAuthCode + verifyAuthCodeDto.getTelephone());
        if (!verifyAuthCodeDto.getAuthCode().equals(realAuthCode)) {
            throw new MallException(ResultCode.AUTH_CODE_VALIDATE_FAILED);
        }
    }
}
