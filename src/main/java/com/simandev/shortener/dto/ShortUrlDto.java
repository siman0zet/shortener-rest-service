package com.simandev.shortener.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@ApiModel(value = "DTO для вывода коротких URL [ShortUrlDto]")
public class ShortUrlDto {
    @ApiModelProperty(value = "Сгенерированная короткая ссылка")
    String shortUrl;
    @ApiModelProperty(value = "Количество переходов по ссылке")
    Long countTransition;
}
