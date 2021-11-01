package com.simandev.shortener.api;

import com.simandev.shortener.dto.ShortUrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
public class GreetingController {
    private final UrlController urlController;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private String projectUrl = "https://vk.com/id212852260";

    @GetMapping()
    public void sourceUrl(@RequestParam String url){
        ShortUrlDto shortUrlDto = urlController.findOrCreateShortUrl(url);
    }

//    @GetMapping("redirect")
//    public void greeting(HttpServletResponse httpServletResponse) {
//        httpServletResponse.setHeader("Location", projectUrl);
//        httpServletResponse.setStatus(302);
//    }
}
