package com.example.learnmalldemo.service.redis.impl;

import com.example.learnmalldemo.service.redis.IRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class RedisServiceImpl implements IRedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <T> void set(String key, T value, Long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
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
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public Boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    @Override
    public Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Boolean hashSet(String key, String hashKey, Object value, Long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }

    @Override
    public void hashSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Map<Object, Object> hashGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Boolean hashSetAll(String key, Map<Object, Object> struct, Long time) {
        redisTemplate.opsForHash().putAll(key, struct);
        return expire(key, time);
    }

    @Override
    public void hashSetAll(String key, Map<Object, ?> struct) {
        redisTemplate.opsForHash().putAll(key, struct);
    }

    @Override
    public void hashDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hashHasKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Long hashIncrement(String key, Object hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public Long hashDecrement(String key, Object hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public Set<Object> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }
}
