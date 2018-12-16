package com.hfc.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

/**
 * Created by user-hfc on 2018/12/16.
 */
@Configuration
public class CustomCacheingConfig extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
