package com.team1.shortenurl.dao;

import com.team1.shortenurl.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreateAccountMapper {
    User checkEmail(String email);

    User checkUsername(String username);

    void createUser(String email, String username, String password, int usertype);
}
