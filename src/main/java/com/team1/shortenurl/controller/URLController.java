package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSONObject;
import com.team1.shortenurl.entity.Url;
import com.team1.shortenurl.service.ResolveUrlService;
import com.team1.shortenurl.service.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

// @RequestMapping(value = "/urlProcess")

@RestController
@CrossOrigin
public class URLController {
    @Autowired
    ShortenUrlService shortenUrlService;

    @Autowired
    ResolveUrlService resolveUrlService;

    @ResponseBody
    @RequestMapping(value = "/shortenUrl")
    public String UrlShorten(@RequestBody String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String url = jsonObject.getString("url");
        int uid = jsonObject.getIntValue("uid");
        String shortUrl = randG();

        while (shortenUrlService.checkShortUrl(shortUrl) != null) {
            shortUrl = randG();
        }
        shortUrl = "sslt1.herokuapp.com/" + shortUrl;
        shortenUrlService.createShortenUrl(uid, url, shortUrl);
        JSONObject res = new JSONObject();
        res.put("shortUrl", shortUrl);
        return res.toJSONString();
    }

    public static String randG() {
        long min = 3521614606208L;
        long max = 218340105584895L;
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] ch = base62.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (rangeLong > 0) {
            long i = rangeLong % 62;
            String str = String.valueOf(i);
            int ii = Integer.parseInt(str);
            rangeLong /= 62;
            sb.append(ch[ii]);
        }
        return String.valueOf(sb);
    }

    @ResponseBody
    @RequestMapping(value = "/{shortUrl}")
    public void UrlResolve(@PathVariable("shortUrl") String shortUrl, HttpServletResponse response) throws IOException {
        shortUrl = "sslt1.herokuapp.com/" + shortUrl;
        Url url = resolveUrlService.resolve(shortUrl);
        if (url == null) {
            response.sendRedirect("/public/error/404.html");
            return;
        }
        System.out.println(url.getCountClick());
        System.out.println(url.getServiceId());
        resolveUrlService.updateCount(url.getCountClick() + 1, url.getServiceId());
        String longUrl = url.getLongUrl();
        response.sendRedirect(longUrl);
    }

    @ResponseBody
    @RequestMapping(value = "/diyShortenUrl")
    public String diyShortenUrl(@RequestBody String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        int uid = jsonObject.getIntValue("uid");
        String longUrl = jsonObject.getString("longUrl");
        String shortUrl = jsonObject.getString("shortUrl");
        shortUrl = "sslt1.herokuapp.com/" + shortUrl;

        Url url = shortenUrlService.checkShortUrl(shortUrl);
        JSONObject res = new JSONObject();
        if(Objects.isNull(url)){
            shortenUrlService.createShortenUrl(uid, longUrl, shortUrl);
            res.put("status", "Success");
        }else{
            res.put("status", "Failure");
        }
        return res.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/batchShorten")
    public String BatchUrlShorten(@RequestBody MyRequestData requestData){
        String uid = requestData.getUid();
        String[] jsonData = requestData.getJsonData();
        System.out.println(uid);
        for(String data: jsonData){
            System.out.println(data);
        }
        return "nb";
    }

    private static class MyRequestData {
        private String uid;
        private String[] jsonData;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String[] getJsonData() {
            return jsonData;
        }

        public void setJsonData(String[] jsonData) {
            this.jsonData = jsonData;
        }
    }
}
