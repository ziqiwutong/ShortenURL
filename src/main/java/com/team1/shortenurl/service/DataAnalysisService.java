package com.team1.shortenurl.service;

import com.team1.shortenurl.entity.Url;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataAnalysisService {
    List<Url> queryLastYear(int uid);

}
