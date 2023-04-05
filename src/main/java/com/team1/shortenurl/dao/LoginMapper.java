package com.team1.shortenurl.dao;

import org.apache.ibatis.annotations.Mapper;
import com.team1.shortenurl.entity.User;

import java.util.List;

@Mapper
public interface LoginMapper {
    User queryUser(int uid);
}
