package com.team1.shortenurl.service;

import com.team1.shortenurl.entity.Url;
import org.springframework.stereotype.Service;

@Service
public interface ResolveUrlService {
    Url resolve(String shortUrl);

    void updateCount(int countClick, int service_id);
}
