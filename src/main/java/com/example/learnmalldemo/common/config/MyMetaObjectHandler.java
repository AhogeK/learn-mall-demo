package com.example.learnmalldemo.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 通用字段填充
 *
 * @author AhogeK ahogek@gmail.com
 * @version 1.00 | 2021-06-21 15:57
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }
}
