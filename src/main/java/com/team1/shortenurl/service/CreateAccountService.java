package com.team1.shortenurl.service;

import com.team1.shortenurl.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CreateAccountService {

    // Check if email exists
    User checkEmail(String email);

    // Check if username exists
    User checkUsername(String username);

    // Create new user
    void createUser(String email, String username, String password);
}
