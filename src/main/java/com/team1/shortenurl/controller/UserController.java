package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSONObject;
import com.team1.shortenurl.entity.User;
import com.team1.shortenurl.service.CreateAccountService;
import com.team1.shortenurl.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    LoginService loginService;

    @Resource
    CreateAccountService createAccountService;

    @ResponseBody
    @RequestMapping(value = "/createAccount")
    public String createAccount(@RequestBody String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");

        if (Objects.isNull(createAccountService.checkEmail(email)) && Objects.isNull(createAccountService.checkUsername(username))){
            createAccountService.createUser(email, username, password);
            JSONObject res = new JSONObject();
            res.put("status", "Create user success");
            return res.toJSONString();
        }else {
            JSONObject res = new JSONObject();
            res.put("status", "Create user failure");
            return res.toJSONString();
        }
    }
}
