package com.example.learnmalldemo.service;

/**
 * <p>
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-11 15:38
 * @since 1.00
 */
public interface RedisService {

    /**
     * 存储数据
     *
     * @param key key
     * @param value value
     */
    void set(String key, String value);

    /**
     * 获取数据
     *
     * @param key key
     * @return value
     */
    String get(String key);

    /**
     * 设置超期时间
     *
     * @param key key
     * @param expire expire time
     * @return is success
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     *
     * @param key key
     */
    void remove(String key);

    /**
     * 自增操作
     *
     * @param key key
     * @param delta 自增步长
     * @return result value
     */
    Long increment(String key, long delta);
}
