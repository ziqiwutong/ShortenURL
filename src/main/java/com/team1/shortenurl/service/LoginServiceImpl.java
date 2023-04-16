package com.team1.shortenurl.service;

import com.team1.shortenurl.dao.LoginMapper;
import com.team1.shortenurl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User userLogin(String username, String password){
        User user = queryUserWithName(username);
        if(user == null || !Objects.equals(user.getPassword(), password)){
            return null;
        }
        return user;
    };

    @Override
    public User queryUserWithName(String username){
        return this.loginMapper.queryUserWithName(username);
    };
}
