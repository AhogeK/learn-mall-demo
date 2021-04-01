package com.example.learnmalldemo.common.api;

/**
 * <p>
 * 封装 API 错误码
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-25 23:05
 * @since 1.00
 */
public interface IErrorCode {

    /**
     * 获取 API code
     *
     * @return api code
     */
    Long getCode();

    /**
     * 获取 API 消息
     *
     * @return api message
     */
    String getMessage();
}
