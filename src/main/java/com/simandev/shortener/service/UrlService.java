package com.simandev.shortener.service;

import com.simandev.shortener.entity.Url;

import java.util.List;
import java.util.Optional;

public interface UrlService {
    Url create(String sourceUrl);
    List<Url> getAll();
    Url updateCounter(String id, Long counter);
    Url get(String id);
    void delete(String id);
}
