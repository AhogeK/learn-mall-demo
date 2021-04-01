package com.example.learnmalldemo.common.api;

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
public enum ResultCode implements IErrorCode{
    /*
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /*
     * 操作失败
     */
    FAILED(500, "操作失败"),
    /*
     * 参数校验失败
     */
    VALIDATE_FAILED(404, "参数检验失败"),
    /*
     * token过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /*
     * 每有相关权限
     */
    FORBIDDEN(403, "没有相关权限");

    private final Long code;

    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
