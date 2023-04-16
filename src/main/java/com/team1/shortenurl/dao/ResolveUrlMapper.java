package com.team1.shortenurl.dao;

import com.team1.shortenurl.entity.Url;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResolveUrlMapper {
    Url resolve(String shortUrl);

    void updateCount(int countClick, int service_id);
}
