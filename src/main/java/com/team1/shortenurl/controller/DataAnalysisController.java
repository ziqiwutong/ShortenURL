package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.team1.shortenurl.entity.Url;
import com.team1.shortenurl.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

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

        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ago = Integer.parseInt(dateFormat.format(date).split("-")[0]) - 1 + dateFormat.format(date).substring(4);
        String year = ago.split("-")[0];
        int monthTmp = (Integer.valueOf(ago.split("-")[1]) + 1) > 12 ? (Integer.valueOf(ago.split("-")[1]) + 1) % 12 : (Integer.valueOf(ago.split("-")[1]) + 1);
        String month = "";
        if(monthTmp < 10){
            month = "0" + String.valueOf(monthTmp);
        }else month = String.valueOf(monthTmp);

        String begin = year + "-" + month;

        JSONArray jsonArray = new JSONArray();

        List<Url> list = this.dataAnalysisService.queryLastYear(uid);

        Map<String, Integer> map = new TreeMap<>();
        for(int i = 0; i < 12; i++){
            map.put(begin, 0);
            int nm = Integer.parseInt(begin.split("-")[1]) + 1;
            int ny = Integer.parseInt(begin.split("-")[0]);
            if(nm > 12){
                nm -= 12;
                ny++;
            }
            String nextMonth = "";
            if(nm < 10){
                nextMonth = "0" + nm;
            }else nextMonth = String.valueOf(nm);
            begin = ny + "-" + nextMonth;
        }
        for (Url url : list) {
            String ct = url.getCreateTime().substring(0, 7);
            if(map.containsKey(ct)) map.put(ct, map.get(ct) + 1);
        }
        for(String str: map.keySet()){
            JSONObject object = new JSONObject();
            object.put("month", str);
            object.put("count", map.get(str));
            jsonArray.add(object);
        }
        return jsonArray.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/queryTop5Url")
    public String queryTop5Url(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        int uid = jsonObject.getIntValue("uid");
        List<Url> list = this.dataAnalysisService.queryTop5(uid);
        for (Url url: list){
            System.out.println(url.toString());
        }
        return "nb";
    }
}
