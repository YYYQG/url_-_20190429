package com.xxx.dao.repository;

import com.xxx.dao.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Integer> {

    public Link findLinkByShortUrl(String shortUrl);
    public Link findLinkByLongUrl(String longUrl);

}
