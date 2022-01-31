package com.example.learnmalldemo.service.mongo.impl;

import com.example.learnmalldemo.common.api.ResultCode;
import com.example.learnmalldemo.dto.admin.MemberReadHistoryAddDto;
import com.example.learnmalldemo.entity.admin.UmsAdmin;
import com.example.learnmalldemo.entity.mongo.MemberReadHistory;
import com.example.learnmalldemo.exception.MallException;
import com.example.learnmalldemo.repository.mongo.MemberReadHistoryRepository;
import com.example.learnmalldemo.service.mongo.IMemberReadHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

/**
 * 会员浏览记录管理 Service 实现类
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-07-12 11:06
 */
@Service
public class MemberReadHistoryServiceImpl implements IMemberReadHistoryService {

    private final MemberReadHistoryRepository memberReadHistoryRepository;

    public MemberReadHistoryServiceImpl(MemberReadHistoryRepository memberReadHistoryRepository) {
        this.memberReadHistoryRepository = memberReadHistoryRepository;
    }

    @Override
    public void add(MemberReadHistoryAddDto memberReadHistoryAddDto, UmsAdmin umsAdmin) {
        MemberReadHistory memberReadHistory = new MemberReadHistory();
        BeanUtils.copyProperties(memberReadHistoryAddDto, memberReadHistory);
        memberReadHistory.setMemberId(umsAdmin.getId());
        memberReadHistory.setMemberIcon(umsAdmin.getIcon());
        memberReadHistory.setMemberNickname(umsAdmin.getNickName());
        memberReadHistory.setCreateTime(new Date());
        try {
            memberReadHistoryRepository.save(memberReadHistory);
        } catch (Exception e) {
            throw new MallException(ResultCode.INSERT_FAILED);
        }
    }

    @Override
    public void removeBatchById(String[] ids) {
        Stream.of(ids).forEach(memberReadHistoryRepository::deleteById);
    }

    @Override
    public Page<MemberReadHistory> page(Long memberId, Integer pageNum, Integer pageSize) {
        return memberReadHistoryRepository
                .findByMemberIdOrderByCreateTimeDesc(memberId, PageRequest.of(pageNum, pageSize));
    }
}
