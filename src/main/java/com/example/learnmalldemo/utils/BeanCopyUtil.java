package com.example.learnmalldemo.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Bean 拷贝工具类
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-15 16:32
 * @since 1.00
 */
public class BeanCopyUtil {

    /**
     * 工具类禁止实例化
     */
    private BeanCopyUtil() {
        throw new IllegalStateException("工具类");
    }

    /**
     * 创建属性拷贝后的集合
     *
     * @param source 源数据集合
     * @param target 目标字节类
     * @param <S>    源数据集合类型
     * @param <T>    目标集合类型
     * @return 拷贝后的目标类型集合
     * @author AhogeK ahogek@gmail.com
     * @date 2021-04-15 16:32
     */
    public static <S, T> List<T> createCopyBeanPropCollection(Collection<S> source, Class<T> target) {
        List<T> targetList = new ArrayList<>();
        source.forEach(item -> {
            T targetInstance = BeanUtils.instantiateClass(target);
            BeanUtils.copyProperties(item, targetInstance);
            targetList.add(targetInstance);
        });
        return targetList;
    }
}