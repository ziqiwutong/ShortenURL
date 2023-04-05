package com.team1.shortenurl.service;

import com.team1.shortenurl.dao.LoginMapper;
import com.team1.shortenurl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService{
//    @Resource
//    private LoginMapper loginMapper;

    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User queryUser(int uid){
        return this.loginMapper.queryUser(uid);
    };
}
