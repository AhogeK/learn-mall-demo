package com.example.learnmalldemo.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 校验验证码
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-13 14:20
 * @since 1.00
 */
@Data
@Schema(description = "校验验证码表单参数实体")
public class VerifyAuthCodeDto {

    @NotBlank(message = "{notnull}")
    @Schema(description = "手机号")
    private String telephone;

    @NotBlank(message = "{notnull}")
    @Schema(description = "验证码")
    private String authCode;

    public VerifyAuthCodeDto() {
    }

    public VerifyAuthCodeDto(String telephone, String authCode) {
        this.telephone = telephone;
        this.authCode = authCode;
    }
}
