package com.simandev.shortener.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShortUrlDto {
    String shortUrl;
    Long countTransition;
}
