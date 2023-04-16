package com.team1.shortenurl.service;

import com.team1.shortenurl.dao.ShortenUrlMapper;
import com.team1.shortenurl.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenUrlServiceImpl implements ShortenUrlService{
    @Autowired
    ShortenUrlMapper shortenUrlMapper;

    @Override
    public void createShortenUrl(int uid, String longUrl, String shortUrl){
        this.shortenUrlMapper.createShortenUrl(uid, longUrl, shortUrl, 0);
    }

    @Override
    public Url checkShortUrl(String shortUrl){
        return this.shortenUrlMapper.checkShortUrl(shortUrl);
    }
}
