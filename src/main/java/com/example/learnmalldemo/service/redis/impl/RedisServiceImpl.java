package com.example.learnmalldemo.service.redis.impl;

import com.example.learnmalldemo.service.redis.IRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
@RequiredArgsConstructor
public class RedisServiceImpl implements IRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public <T> void set(String key, T value) {
        /*
         * 'opsForValue' Returns the operations performed on simple values (or Strings in Redis terminology).
         */
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(redisTemplate.opsForValue().get(key));
    }

    @Override
    public boolean expire(String key, long expire) {
        Boolean expireBoolean = redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(expireBoolean);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
}
