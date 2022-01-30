package com.example.learnmalldemo.service.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * 设置完整 hash 结构不带时间
     *
     * @param key    redis 键
     * @param struct hash 结构体
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 16:03
     */
    void hashSetAll(String key, Map<Object, ?> struct);

    /**
     * 删除 hash 结构 key
     *
     * @param key     redis 键
     * @param hashKey hash 结构键
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 16:06
     */
    void hashDel(String key, Object... hashKey);

    /**
     * 判断hash结构中是否有该属性
     *
     * @param key     redis 键
     * @param hashKey redis hash 结构属性键
     * @return 是否拥有
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 16:10
     */
    Boolean hashHasKey(String key, Object hashKey);

    /**
     * hash结构中属性自增
     *
     * @param key     redis 键
     * @param hashKey hash 结构属性键
     * @param delta   自增步长
     * @return null 或 处理数
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 16:25
     */
    Long hashIncrement(String key, Object hashKey, Long delta);

    /**
     * hash 结构中属性自减
     *
     * @param key     redis键
     * @param hashKey hash 结构属性键
     * @param delta   自减步长
     * @return null 或 处理数
     * @author AhogeK ahgoek@gmail.com
     * @date 2022-01-28 16:32
     */
    Long hashDecrement(String key, Object hashKey, Long delta);

    /**
     * 获取 set 结构
     *
     * @param key redis 键
     * @return set 结构集合
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 16:37
     */
    Set<Object> setMembers(String key);

    /**
     * 向 set 结构中添加属性
     *
     * @param key    redis 键
     * @param values 结构属性值数组
     * @return null 或 处理数
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 16:41
     */
    Long setAdd(String key, Object... values);

    /**
     * 确认是否是 set 结构属性
     *
     * @param key   redis 键
     * @param value 结构属性值
     * @return 是否是 set 结构属性
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-28 16:57
     */
    Boolean setIsMember(String key, Object value);

    /**
     * 获取指定键下 set 结构的长度
     *
     * @param key redis 键
     * @return set 结构长度
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-30
     */
    Long setSize(String key);

    /**
     * 删除 set 结构中的元素
     *
     * @param key    redis set 键
     * @param values set 结构元素
     * @return null 或 处理数
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-30 15:35
     */
    Long setRemove(String key, Object... values);

    /**
     * 获取指定范围 list 结构属性
     *
     * @param key   redis list 键
     * @param start 开始位置
     * @param end   结束位置
     * @return 指定范围下的 list 结构属性
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-30 15:39
     */
    List<Object> listRange(String key, Long start, Long end);

    /**
     * 获取 list 结构长度
     *
     * @param key redis list 键
     * @return list 结构长度
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-30 15:46
     */
    Long listSize(String key);

    /**
     * 获取 list 结构指定索引下的属性
     *
     * @param key   redis list 键
     * @param index 下标
     * @return 指定索引下的属性
     * @author AhogeK ahogek@gmail.com
     * @date 2022-01-30 15:39
     */
    Object listIndex(String key, Long index);
}
