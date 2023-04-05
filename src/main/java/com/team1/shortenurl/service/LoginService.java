package com.team1.shortenurl.service;

import com.team1.shortenurl.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    User queryUser(int uid);
}
