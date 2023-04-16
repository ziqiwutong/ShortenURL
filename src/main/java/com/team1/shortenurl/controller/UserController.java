package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSONObject;
import com.team1.shortenurl.entity.User;
import com.team1.shortenurl.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/queryUser")
    public String queryUser(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        int uid = jsonObject.getIntValue("uid");
        User user = loginService.queryUser(uid);

        JSONObject res = new JSONObject();
        res.put("uid", user.getUid());
        res.put("urls_id", user.getUrls_id());
        res.put("name", user.getUsername());
        res.put("password", user.getPassword());
        res.put("type", user.getType());
        res.put("email", user.getEmail());
        res.put("login_status", user.getLogin_status());
        res.put("createTime", user.getCreateTime());
        res.put("updateTime", user.getUpdateTime());

        System.out.println("nb");

        return res.toJSONString();
    }
}
