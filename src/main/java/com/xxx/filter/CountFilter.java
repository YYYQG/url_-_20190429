package com.xxx.filter;

import com.xxx.dao.entity.Link;
import com.xxx.dao.repository.LinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CountFilter extends OncePerRequestFilter {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request,response);
        if(response.getStatus()==302){
            String longUrl = response.getHeader("Location");
            longUrl=StringUtils.substringAfterLast(longUrl,"/");
            log.info(longUrl);
            Link link = linkRepository.findLinkByLongUrl(longUrl);
            int count = Integer.parseInt(link.getCount())+1;
            link.setCount(String.valueOf(count));
            linkRepository.save(link);
        }

    }
}
