package com.team1.shortenurl.service;

import com.team1.shortenurl.dao.CreateAccountMapper;
import com.team1.shortenurl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountServiceImpl implements CreateAccountService {
    @Autowired
    CreateAccountMapper createAccountMapper;

    @Override
    public User checkEmail(String email) {
        return this.createAccountMapper.checkEmail(email);
    }

    @Override
    public User checkUsername(String username) {
        return this.createAccountMapper.checkUsername(username);
    }

    @Override
    public void createUser(String email, String username, String password) {

        this.createAccountMapper.createUser(email, username, password);
    }
}
