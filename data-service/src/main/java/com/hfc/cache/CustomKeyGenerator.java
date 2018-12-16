package com.hfc.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created by user-hfc on 2018/12/16.
 */
@Configuration
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        return CustomKey(method, objects);
    }

    static final String CustomKey(Method method, Object... objects) {
        StringBuilder key = null;
        key = new StringBuilder(method.getName());
        for (Object obj : objects) {
            key.append(obj.toString());
        }
        return key.toString();
    }
}
