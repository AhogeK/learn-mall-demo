package com.example.learnmalldemo.dto.portal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 注册 DTO
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2022-01-31 19:29
 */
@Data
@Schema(description = "注册 DTO")
public class RegisterDto {

    @Schema(description = "用户名")
    @Size(min = 3, max = 32, message = "{out-range}")
    @NotNull(message = "{notnull}")
    private String username;

    @Schema(description = "密码")
    @NotNull(message = "{notnull}")
    @Size(min = 6, max = 64, message = "{out-range}")
    private String password;

    @Schema(description = "手机号码")
    @Size(min = 11, max = 16, message = "{not-range}")
    private String telephone;

    @Schema(description = "认证code")
    @NotNull(message = "{notnull}")
    private String authCode;
}
