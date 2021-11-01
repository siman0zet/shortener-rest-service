package com.simandev.shortener.service;

import com.simandev.shortener.entity.Url;
import com.simandev.shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Override
    @Transactional
    public Url create(String sourceUrl) {
        return urlRepository.save(Url.builder()
                .shortURL(randomShortUrl())
                .sourceURL(sourceUrl)
                .countTransition(0L)
                .build());
    }

    private String randomShortUrl(){
        return RandomStringUtils.randomAlphanumeric(5);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Url> getAll() {
        return urlRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Url updateCounter(String id, Long counter) {
        Url url = urlRepository.getById(id);
        url.setCountTransition(counter);
        return urlRepository.save(url);
    }

    public Url findSourceUrl(String sourceUrl){
        List<Url> urlList = urlRepository.findAll();
        return urlList.stream().filter(url -> sourceUrl.equals(url.getSourceURL())).findAny().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Url get(String shortUrl) {
        return urlRepository.findById(shortUrl).orElse(null);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(String id) {
        urlRepository.delete(urlRepository.getById(id));
    }
}
