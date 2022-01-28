package com.example.learnmalldemo.service.redis;

import java.util.List;
import java.util.Map;

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
     * 设置超时时间
     *
     * @param key    key
     * @param expire expire time (seconds)
     * @return 设置是否成功
     */
    Boolean expire(String key, long expire);

    /**
     * 获取过期时间
     *
     * @param key 被获取过期时间的key
     * @return 过期时间 (s)
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-25 19:17
     */
    Long getExpire(String key);

    /**
     * 判断是否有 key
     *
     * @param key redis 键名
     * @return 是否有该键
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-25 19:23
     */
    Boolean hasKey(String key);


    /**
     * 自增操作
     *
     * @param key   key
     * @param delta 自增步长
     * @return result value
     */
    Long increment(String key, long delta);

    /**
     * 按 delta 递减
     *
     * @param key   redis 键
     * @param delta 步长
     * @return 结果值
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-25 19:30
     */
    Long decrement(String key, long delta);

    /**
     * 获取 hash 结构中的属性
     *
     * @param key     redis 键
     * @param hashKey hash 键
     * @return hash结构属性
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-25 19:35
     */
    Object hashGet(String key, String hashKey);

    /**
     * 设置 hash 结构中的属性 (带有效时间)
     *
     * @param key     hash键
     * @param hashKey hash结构属性键
     * @param value   值
     * @param time    存储时间 (秒）
     * @return 是否成功
     */
    Boolean hashSet(String key, String hashKey, Object value, Long time);

    /**
     * hash 结构属性设置
     *
     * @param key     hash结构键
     * @param hashKey hash结构属性键
     * @param value   值
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-26 23:22
     */
    void hashSet(String key, String hashKey, Object value);

    /**
     * 获取指定键的 hash 结构
     *
     * @param key hash结构键值
     * @return 指定键的 hash 结构
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-27 12:23
     */
    Map<Object, Object> hashGetAll(String key);

    /**
     * 设置完整 hash 结构
     *
     * @param key    redis 键
     * @param struct hash完整结构
     * @param time   存储有效时间 (秒)
     * @return 设置是否成功
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 11:41
     */
    Boolean hashSetAll(String key, Map<Object, Object> struct, Long time);
}
