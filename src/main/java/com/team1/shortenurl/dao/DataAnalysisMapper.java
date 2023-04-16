package com.team1.shortenurl.dao;

import com.team1.shortenurl.entity.Url;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataAnalysisMapper {
    List<Url> queryLastYear(int uid);
}
