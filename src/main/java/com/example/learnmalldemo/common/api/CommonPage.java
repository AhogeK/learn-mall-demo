package com.example.learnmalldemo.common.api;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>
 * 分页数据封装类
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-25 22:30
 * @since 1.00
 */
@Data
public class CommonPage<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Integer pages;

    private Long total;

    private List<T> list;

    /**
     * 将 PageHelper 分页后的 list 转为分页信息
     *
     * @param list 分页数据
     * @param <T>  分页数据类型
     * @return 分页数据分装类
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        BeanUtils.copyProperties(pageInfo, result);
        return result;
    }
}
