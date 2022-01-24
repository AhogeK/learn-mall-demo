package com.example.learnmalldemo.service.redis;

import java.util.List;

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
public interface IRedisService {

    /**
     * 存储数据
     *
     * @param key   key
     * @param value value
     */
    <T> void set(String key, T value);

    /**
     * 存储数据
     *
     * @param key   redis 键名
     * @param value 值
     * @param time  存储时间 （秒）
     * @param <T>   值类型
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-21 19:37
     */
    <T> void set(String key, T value, Long time);

    /**
     * 默认获取方法 返回 String 类型
     *
     * @param key 键名
     * @return String值
     */
    String get(String key);

    /**
     * 获取数据
     *
     * @param key   redis 键名
     * @param clazz 获取数据类型
     * @param <T>   返回的类型
     * @return 获取到的数据 T
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 删除指定 key
     *
     * @param key redis 键名
     * @return 是否删除成功
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-22
     */
    Boolean del(String key);

    /**
     * 批量删除 key
     *
     * @param keys redis 键名 list
     * @return 成功删除的数量
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-24 23:07
     */
    Long del(List<String> keys);


    /**
     * 设置超期时间
     *
     * @param key    key
     * @param expire expire time
     */
    void expire(String key, long expire);


    /**
     * 自增操作
     *
     * @param key   key
     * @param delta 自增步长
     * @return result value
     */
    Long increment(String key, long delta);
}
