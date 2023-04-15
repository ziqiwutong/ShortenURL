package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSONObject;
import com.team1.shortenurl.entity.Url;
import com.team1.shortenurl.service.ResolveUrlService;
import com.team1.shortenurl.service.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

// @RequestMapping(value = "/urlProcess")

@RestController
public class URLController {
    @Autowired
    ShortenUrlService shortenUrlService;

    @Autowired
    ResolveUrlService resolveUrlService;

    @ResponseBody
    @RequestMapping(value = "/shortenUrl")
    public String UrlShorten(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String url = jsonObject.getString("url");
        int uid = jsonObject.getIntValue("uid");
        String shortUrl = randG();

        while(shortenUrlService.checkShortUrl(shortUrl) != null){
            shortUrl = randG();
        }
        shortUrl = "heroku.team1.com/" + shortUrl;
        shortenUrlService.createShortenUrl(uid, url, shortUrl);
        JSONObject res = new JSONObject();
        res.put("shortUrl", shortUrl);
        return res.toJSONString();
    }

    public static String randG(){
        long min = 3521614606208L;
        long max = 218340105584895L;
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] ch = base62.toCharArray();
        StringBuilder sb = new StringBuilder();
        while(rangeLong > 0){
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
        shortUrl = "heroku.team1.com/" + shortUrl;
        Url url = resolveUrlService.resolve(shortUrl);
        if(url == null){
            response.sendRedirect("../../../resources/public/error/404.html");
        }
        String longUrl = url.getLongUrl();
        response.sendRedirect(longUrl);
    }
}
