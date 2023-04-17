package com.team1.shortenurl.service;

import com.team1.shortenurl.dao.DataAnalysisMapper;
import com.team1.shortenurl.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService{
    @Autowired
    DataAnalysisMapper dataAnalysisMapper;
    public List<Url> queryLastYear(int uid){
        return this.dataAnalysisMapper.queryLastYear(uid);
    }

    public List<Url> queryTop5(int uid){
        return this.dataAnalysisMapper.queryTop5(uid);
    }

}
