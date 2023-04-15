package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSONObject;
import com.team1.shortenurl.dao.CreateAccountMapper;
import com.team1.shortenurl.entity.User;
import com.team1.shortenurl.service.CreateAccountService;
import com.team1.shortenurl.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    LoginService loginService;

    @Resource
    CreateAccountService createAccountService;
    @ResponseBody
    @RequestMapping(value = "/queryUser")
    public String queryUser(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        int uid = jsonObject.getIntValue("uid");
        User user = loginService.queryUser(uid);

        JSONObject res = new JSONObject();
        res.put("uid", user.getUid());
        res.put("urls_id", user.getUrls_id());
        res.put("name", user.getName());
        res.put("password", user.getPassword());
        res.put("type", user.getType());
        res.put("email", user.getEmail());
        res.put("login_status", user.getLogin_status());
        res.put("createTime", user.getCreateTime());
        res.put("updateTime", user.getUpdateTime());

        System.out.println("nb");

        return res.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/createAccount")
    public String createAccount(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String email = jsonObject.getString("email");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        User emailCheck = createAccountService.checkEmail(email);
        if(emailCheck != null){
            JSONObject res = new JSONObject();
            res.put("status", "Email already exists");
            return res.toJSONString();
        }

        User usernameCheck = createAccountService.checkEmail(username);
        if(usernameCheck != null) {
            JSONObject res = new JSONObject();
            res.put("status", "Username already exists");
            return res.toJSONString();
        }

        return "temp";

    }
}
