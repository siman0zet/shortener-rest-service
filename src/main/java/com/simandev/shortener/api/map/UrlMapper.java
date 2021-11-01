package com.simandev.shortener.api.map;

import com.simandev.shortener.dto.ShortUrlDto;
import com.simandev.shortener.dto.UrlDto;
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

    public UrlDto mapToUrlDto(Url sourceUrl){
        if (sourceUrl == null){
            return null;
        }
        return UrlDto.builder()
                .shortUrl(sourceUrl.getShortURL())
                .sourceUrl(sourceUrl.getSourceURL())
                .countTransition(sourceUrl.getCountTransition())
                .build();
    }
}
