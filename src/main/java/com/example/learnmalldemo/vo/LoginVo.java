package com.example.learnmalldemo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登录成功返回 vo
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2020-01-14 23:54
 */
@Data
@Schema(description = "用户登录成功返回")
public class LoginVo {

    @Schema(description = "token")
    private String token;

    @Schema(description = "token前缀")
    private String tokenHead;
}
