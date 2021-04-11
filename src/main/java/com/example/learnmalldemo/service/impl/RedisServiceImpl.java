package com.example.learnmalldemo.service.impl;

import com.example.learnmalldemo.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Redis 服务层实现类
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-11 15:41
 * @since 1.00
 */
@Service
public class RedisServiceImpl implements RedisService {

    /**
     * String-focused extension of RedisTemplate. Since most operations against Redis are String based,
     * this class provides a dedicated class that minimizes configuration of its more generic template
     * especially in terms of serializers.
     */
    private final StringRedisTemplate stringRedisTemplate;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value) {
        /*
         * 'opsForValue' Returns the operations performed on simple values (or Strings in Redis terminology).
         */
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        Boolean expireBoolean = stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return !Objects.isNull(expireBoolean) ? expireBoolean : false;
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }
}
