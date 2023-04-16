package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.team1.shortenurl.entity.Url;
import com.team1.shortenurl.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// @RequestMapping(value = "/dataAnalysis")
@RestController
public class DataAnalysisController {

    @Autowired
    DataAnalysisService dataAnalysisService;

    @ResponseBody
    @RequestMapping(value = "/queryLastYear")
    public String userActivityIn12Month(@RequestBody String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        int uid = jsonObject.getIntValue("uid");

        //long timestamp = System.currentTimeMillis();
        //Date date = new Date(timestamp);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String ago = Integer.parseInt(dateFormat.format(date).split("-")[0]) - 1 + dateFormat.format(date).substring(4);

        List<Url> list = this.dataAnalysisService.queryLastYear(uid);
        for (Url url : list) {
            System.out.println(url.getShortUrl());
        }
        JSONObject res = new JSONObject();
        res.put("nb", "nb");
        return res.toJSONString();
    }
}
