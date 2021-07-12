package com.example.learnmalldemo.service.mongo;

import com.example.learnmalldemo.entity.UmsAdmin;
import com.example.learnmalldemo.entity.mongo.MemberReadHistory;
import com.example.learnmalldemo.form.MemberReadHistoryAddForm;
import org.springframework.data.domain.Page;

/**
 * 会员浏览记录管理 Service
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 11:05
 */
public interface IMemberReadHistoryService {

    /**
     * 新增会员浏览记录
     *
     * @param memberReadHistoryAddForm 会员浏览记录新增表单
     * @param umsAdmin                 浏览用户
     */
    void add(MemberReadHistoryAddForm memberReadHistoryAddForm, UmsAdmin umsAdmin);

    /**
     * 批量上出会员浏览记录
     *
     * @param ids 浏览记录id数组
     */
    void removeBatchById(String[] ids);

    /**
     * 查询会员浏览记录分页
     *
     * @param memberId 会员id
     * @param pageNum  当前页
     * @param pageSize 分页每页大小
     * @return 会员浏览记录分页
     */
    Page<MemberReadHistory> page(Long memberId, Integer pageNum, Integer pageSize);
}
