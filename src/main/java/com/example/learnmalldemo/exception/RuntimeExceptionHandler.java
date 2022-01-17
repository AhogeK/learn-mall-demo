package com.example.learnmalldemo.exception;

import com.example.learnmalldemo.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
public class RuntimeExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult<Void> handle(Exception e) {
        e.printStackTrace();
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResult<Void> handle(RuntimeException e) {
        e.printStackTrace();
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public CommonResult<Void> handle(UsernameNotFoundException e) {
        e.printStackTrace();
        return CommonResult.failed(e.getMessage());
    }

    @ExceptionHandler(MallException.class)
    public CommonResult<Void> handle(MallException e) {
        e.printStackTrace();
        return CommonResult.failed(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Void> notValidExceptionHandle(MethodArgumentNotValidException e) {
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        return CommonResult.failed(Objects.requireNonNull(bindingResult.getFieldError())
                .getField() + " " + Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult<Void> handle(AccessDeniedException e) {
        e.printStackTrace();
        return CommonResult.unauthorized();
    }

    @ExceptionHandler(IllegalStateException.class)
    public CommonResult<Void> illegalStateExceptionHandle(IllegalStateException e) {
        e.printStackTrace();
        return CommonResult.forbidden();
    }
}
