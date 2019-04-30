package com.xxx.service;

import com.xxx.dao.entity.Link;

public interface LinkService {

    Link generatorShortUrl(String longUrl);
    Link findUrlByShortUrl(String shortUrl);
    Link generatorShortUrlByCustomer(String longUrl,String shortUrl);
}
