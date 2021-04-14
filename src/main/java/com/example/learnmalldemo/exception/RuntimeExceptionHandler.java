package com.example.learnmalldemo.exception;

import com.example.learnmalldemo.common.api.CommonResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * <p>
 * 运行时异常控制器
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-13 16:27
 * @since 1.00
 */
@RestController
@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public CommonResult<Void> handle(RuntimeException e) {
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(MallException.class)
    public CommonResult<Void> handle(MallException e) {
        return CommonResult.failed(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Void> notValidExceptionHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return CommonResult.failed(Objects.requireNonNull(bindingResult.getFieldError())
                .getField() + " " + Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }
}
