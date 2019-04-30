package com.xxx.controller;

import com.xxx.dao.entity.Link;
import com.xxx.exception.ShortUrlNotFindException;
import com.xxx.properties.ShortUrlProperties;
import com.xxx.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/shortUrl")
    @ResponseBody
    public ResponseEntity<Link> generatorShortUrl(String longUrl){
        Link link = linkService.generatorShortUrl(longUrl);
        return ResponseEntity.ok(link);
    }

    @PostMapping("/shortUrlByCustomer")
    @ResponseBody
    public ResponseEntity<Link> generatorShortUrl(String longUrl,String shortUrl){
        Link link = linkService.generatorShortUrlByCustomer(longUrl,shortUrl);
        return ResponseEntity.ok(link);
    }

    @GetMapping("/{shortUrl}")
    public ModelAndView redirect(@PathVariable String shortUrl){
        Link link = linkService.findUrlByShortUrl(shortUrl);
        if(link==null){
            throw new ShortUrlNotFindException("shortUrl not find:"+shortUrl);
        }
        return new ModelAndView("redirect:http://" + link.getLongUrl());
    }

}
