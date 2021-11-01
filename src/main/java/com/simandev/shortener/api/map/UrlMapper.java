package com.simandev.shortener.api.map;

import com.simandev.shortener.dto.ShortUrlDto;
import com.simandev.shortener.entity.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {
    public ShortUrlDto mapToShortUrlDto(Url sourceUrl){
        if (sourceUrl == null){
            return null;
        }
        return ShortUrlDto.builder()
                .shortUrl(sourceUrl.getShortURL())
                .countTransition(sourceUrl.getCountTransition())
                .build();
    }
}
