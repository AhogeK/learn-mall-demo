package com.example.learnmalldemo.service.redis;

import org.junit.jupiter.api.Assertions;

/**
 * redis service 单元测试方法
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2022-01-19 11:07
 */
public class RedisTestService {

    private final IRedisService redisService;

    public RedisTestService(IRedisService redisService) {
        this.redisService = redisService;
    }

    public void setTest() {
        Assertions.assertDoesNotThrow(() -> redisService.set("test", "Just Test"));
    }
}
