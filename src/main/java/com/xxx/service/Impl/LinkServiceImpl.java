package com.xxx.service.Impl;

import com.xxx.dao.entity.Link;
import com.xxx.dao.repository.LinkRepository;
import com.xxx.exception.ShortUrlIsExistException;
import com.xxx.properties.ShortUrlProperties;
import com.xxx.service.LinkService;
import com.xxx.util.ShortUrlGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ShortUrlProperties shortUrlProperties;

    @Override
    public Link generatorShortUrl(String longUrl) {
        //判断是否存在
        Link resultLink = linkRepository.findLinkByLongUrl(longUrl);
        if (resultLink != null) {
            return resultLink;
        }
        String[] result = shortUrlGenerator.shortString(longUrl, shortUrlProperties.getShortUrlLength(), shortUrlProperties.getShortUrlCharArray());
        int index = (int) (Math.random() * 4);
        Link link = linkRepository.findLinkByShortUrl(result[index]);
        while (link != null) {
            result = shortUrlGenerator.shortString(longUrl+RandomStringUtils.random(1), shortUrlProperties.getShortUrlLength(), shortUrlProperties.getShortUrlCharArray());
            index = (int) (Math.random() * 4);
            link = linkRepository.findLinkByShortUrl(result[index]);
        }
        resultLink = new Link(0,result[index],longUrl,"0","system");
        return linkRepository.save(resultLink);
    }

    @Override
    public Link findUrlByShortUrl(String shortUrl) {
        return linkRepository.findLinkByShortUrl(shortUrl);
    }

    @Override
    public Link generatorShortUrlByCustomer(String longUrl, String shortUrl) {
        //判断shortUrl是否存在
        Link link = linkRepository.findLinkByShortUrl(shortUrl);
        if (link != null) {
            if(link.getLongUrl().equals(longUrl)){
                return link;
            }else{
                throw new ShortUrlIsExistException("shortUrl is exist:" + shortUrl);
            }
        }
        Link result = linkRepository.findLinkByLongUrl(longUrl);
        if (result != null) {
            result.setShortUrl(shortUrl);
            result.setType("custom");
        } else {
            result = new Link(0, shortUrl, longUrl, "0", "custom");
        }
        linkRepository.save(result);
        return result;
    }
}
