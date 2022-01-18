package com.example.learnmalldemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 状态更新 DTO
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2022-01-18 13:24
 */
@Data
@Schema(description = "状态更新DTO")
public class UpdateFactoryStatusDto {

    @Schema(description = "品牌id列表")
    @NotEmpty(message = "{notnull}")
    private List<Long> ids;

    @Schema(description = "是否是品牌制造商 0->不是；1->是")
    @NotNull(message = "{notnull}")
    @Min(value = 0, message = "{out-range}")
    @Max(value = 1, message = "{out-range}")
    private Integer factoryStatus;
}
