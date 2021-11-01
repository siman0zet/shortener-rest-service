package com.simandev.shortener.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@ApiModel(value = "DTO для вывода полной информации по URL [UrlDto]")
public class UrlDto {
    @ApiModelProperty(value = "Сгенерированная короткая ссылка")
    String shortUrl;
    @ApiModelProperty(value = "Исходная полная ссылка")
    String sourceUrl;
    @ApiModelProperty(value = "Количество переходов по ссылке")
    Long countTransition;
}
