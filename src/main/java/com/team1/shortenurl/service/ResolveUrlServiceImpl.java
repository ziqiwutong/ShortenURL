package com.team1.shortenurl.service;

import com.team1.shortenurl.dao.ResolveUrlMapper;
import com.team1.shortenurl.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResolveUrlServiceImpl implements ResolveUrlService {

    @Autowired
    ResolveUrlMapper resolveUrlMapper;

    @Override
    public Url resolve(String shortUrl) {
        return resolveUrlMapper.resolve(shortUrl);
    }

    @Override
    public void updateCount(int count, int service_id) {
        this.resolveUrlMapper.updateCount(count, service_id);
    }
}
