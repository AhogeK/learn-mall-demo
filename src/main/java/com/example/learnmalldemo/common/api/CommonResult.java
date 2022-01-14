package com.example.learnmalldemo.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 通用返回对象
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-25 22:58
 */
@Schema(description = "通用返回对象")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> {

    @Schema(description = "code值")
    private Integer code;

    @Schema(description = "请求结果描述")
    private String message;

    private T data;

    protected CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    protected CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     *
     * @param data 结果对象
     * @param <T>  结果类型
     * @return 成功结果
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 失败返回结果
     *
     * @param resultCode 错误码
     * @return 失败结果
     */
    public static <T> CommonResult<T> failed(ResultCode resultCode) {
        return new CommonResult<>(resultCode.getCode(), resultCode.getMessage());
    }

    /**
     * 异常返回结果
     *
     * @param code    错误码
     * @param message 错误类型
     * @param <T>     错误数据类型（无）
     * @return 失败结果
     */
    public static <T> CommonResult<T> failed(Integer code, String message) {
        return new CommonResult<>(code, message);
    }

    /**
     * 失败返回结果
     *
     * @param message 错误提示信息
     * @return 失败结果
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), message);
    }

    /**
     * 失败返回结果
     *
     * @return 失败结果
     */
    public static CommonResult<Void> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @return 失败结果
     */
    public static CommonResult<Void> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     * @return 失败结果
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 未登录返回结果
     *
     * @return 失败结果
     */
    public static <T> CommonResult<T> unauthorized() {
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage());
    }

    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
}
