package com.example.learnmalldemo.exception;

import com.example.learnmalldemo.common.api.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 自定义异常类
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-14 10:29
 * @since 1.00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MallException extends RuntimeException{

    private Integer code;

    private String msg;

    public MallException() {
        this.code = ResultCode.FAILED.getCode();
        this.msg = ResultCode.FAILED.getMessage();
    }

    public MallException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MallException(String msg) {
        this.code = ResultCode.FAILED.getCode();
        this.msg = msg;
    }

    public MallException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }
}
