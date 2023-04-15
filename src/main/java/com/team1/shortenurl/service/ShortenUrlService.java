package com.team1.shortenurl.service;

import com.team1.shortenurl.entity.Url;
import org.springframework.stereotype.Service;

@Service
public interface ShortenUrlService {
    void createShortenUrl(int uid, String longUrl, String shortUrl);

    Url checkShortUrl(String shortUrl);
}
