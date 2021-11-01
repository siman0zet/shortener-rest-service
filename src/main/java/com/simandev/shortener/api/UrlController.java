package com.simandev.shortener.api;

import com.simandev.shortener.api.map.UrlMapper;
import com.simandev.shortener.dto.ShortUrlDto;
import com.simandev.shortener.dto.UrlDto;
import com.simandev.shortener.service.UrlServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlServiceImpl urlService;
    private final UrlMapper urlMapper;


    @ApiOperation(value = "Создание короткой ссылки", notes = "Поиск существующей или создание короткой ссылки в БД")
    @PostMapping(value = "create")
    public ShortUrlDto findOrCreateShortUrl(@RequestParam String sourceUrl){
        if (urlService.findSourceUrl(sourceUrl) != null)
            return urlMapper.mapToShortUrlDto(urlService.findSourceUrl(sourceUrl));
        return urlMapper.mapToShortUrlDto(urlService.create(sourceUrl));
    }

    @ApiOperation(value = "Получение всех коротких ссылок", notes = "Поиск существующей или создание короткой ссылки в БД")
    @GetMapping("findAll")
    public List<ShortUrlDto> getAllShortUrl(){
        return urlService.getAll().stream()
                .map(urlMapper::mapToShortUrlDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Получение редиректа на полный URL по короткой ссылке")
    @GetMapping("redirect/{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse){
        Long counter = urlService.get(shortUrl).getCountTransition() + 1;
        urlService.updateCounter(shortUrl, counter);
        httpServletResponse.setStatus(302);
        httpServletResponse.setHeader("Location", urlService.get(shortUrl).getSourceURL());
    }

    @ApiOperation(value = "Получение информации о короткой ссылке")
    @GetMapping("find/{id}")
    public UrlDto getConcreteShortUrl(@PathVariable(name = "id") String shortUrl){
        return urlMapper.mapToUrlDto(urlService.get(shortUrl));
    }

    @ApiOperation(value = "Удаление короткой ссылки")
    @DeleteMapping("delete/{id}")
    public void deleteUrl(@PathVariable(name = "id") String shortUrl){
        urlService.delete(shortUrl);
    }

    @ApiOperation(value = "Получение статистики", notes = "Получение топа из 20 сайтов по количеству переходов")
    @GetMapping("statistics")
    public List<UrlDto> getLinkStatistics(){
        return urlService.getAll().stream()
                .map(urlMapper::mapToUrlDto)
                .sorted(Comparator.comparing(UrlDto::getCountTransition).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

}
