package com.example.learnmalldemo.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 枚举常用 API 操作码
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-25 23:05
 * @since 1.00
 */
@Getter
@AllArgsConstructor
public enum ResultCode{
    /*
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /*
     * 操作失败
     */
    FAILED(500, "操作失败"),
    /*
     * 新增操作失败
     */
    INSERT_FAILED(500, "新增操作失败"),
    /**
     * 更新操作失败
     */
    UPDATE_FAILED(500, "更新操作失败"),
    /**
     * 删除操作失败
     */
    DELETE_FAILED(500, "删除更新失败"),
    /**
     * 查询失败
     */
    SELECT_FAILED(500, "查询失败"),
    /*
     * 参数校验失败
     */
    VALIDATE_FAILED(404, "参数检验失败"),
    /**
     * 验证码校验失败
     */
    AUTH_CODE_VALIDATE_FAILED(404, "验证码校验失败"),
    /*
     * token过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /*
     * 没有相关权限
     */
    FORBIDDEN(403, "没有相关权限");

    private final  Integer code;

    private final String message;
}
