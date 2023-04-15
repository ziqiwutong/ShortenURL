package com.team1.shortenurl.dao;

import com.team1.shortenurl.entity.Url;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShortenUrlMapper {
    void createShortenUrl(int uid, String longUrl, String shortUrl);

    Url checkShortUrl(String shortUrl);
}
