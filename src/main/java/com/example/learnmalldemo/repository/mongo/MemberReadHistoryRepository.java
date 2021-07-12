package com.example.learnmalldemo.repository.mongo;

import com.example.learnmalldemo.entity.mongo.MemberReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 会员商品浏览历史 Repository
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 10:57
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {

    /**
     * 根据会员id按时间倒序获取浏览记录
     *
     * @param memberId 会员id
     * @param page     分页参数
     * @return 会员浏览记录分页
     */
    Page<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId, Pageable page);
}
