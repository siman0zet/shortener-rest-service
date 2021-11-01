package com.simandev.shortener.api;

import com.simandev.shortener.api.map.UrlMapper;
import com.simandev.shortener.dto.ShortUrlDto;
import com.simandev.shortener.entity.Url;
import com.simandev.shortener.service.UrlServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlServiceImpl urlService;
    private final UrlMapper urlMapper;


    @GetMapping("create")
    public ShortUrlDto findOrCreateShortUrl(@RequestParam String sourceUrl){
        if (urlService.findSourceUrl(sourceUrl) != null)
            return urlMapper.mapToShortUrlDto(urlService.findSourceUrl(sourceUrl));
        return urlMapper.mapToShortUrlDto(urlService.create(sourceUrl));
    }

    @GetMapping("findAll")
    public List<ShortUrlDto> getAllShortUrl(){
        return urlService.getAll().stream()
                .map(urlMapper::mapToShortUrlDto)
                .collect(Collectors.toList());
    }

    @GetMapping("redirect")
    public void redirect(@RequestParam String shortUrl, HttpServletResponse httpServletResponse){
        Long counter = urlService.get(shortUrl).getCountTransition() + 1;
        urlService.updateCounter(shortUrl, counter);
        httpServletResponse.setStatus(302);
        httpServletResponse.setHeader("Location", urlService.get(shortUrl).getSourceURL());
    }

    @GetMapping("find")
    public Url getConcreteShortUrl(@RequestParam String shortUrl){
        return urlService.get(shortUrl);
    }

    @GetMapping("delete")
    public void deleteUrl(@RequestParam String shortUrl){
        urlService.delete(shortUrl);
    }

    @GetMapping("statistics")
    public List<Url> getLinkStatistics(){
        return urlService.getAll().stream().sorted(Comparator.comparing(Url::getCountTransition).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

}
