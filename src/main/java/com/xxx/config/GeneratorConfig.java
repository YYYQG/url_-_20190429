package com.xxx.config;

import com.xxx.util.MD5Generator;
import com.xxx.util.ShortUrlGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfig {

    @Bean
    @ConditionalOnMissingBean(ShortUrlGenerator.class)
    public ShortUrlGenerator shortUrlGenerator(){
        return new MD5Generator();
    }

}
