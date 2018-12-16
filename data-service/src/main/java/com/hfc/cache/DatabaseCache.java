package com.hfc.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by user-hfc on 2018/12/15.
 * @CachePut 不检查，直接把key-value存储起来
 * @Cacheable 先检查有没有key的缓存。有的话直接使用，
 *      没有的话先执行再把key-value存储起来
 * @CacheEvict 删除key对应的缓存
 */
@Repository
@CacheConfig(cacheNames = "database")
public class DatabaseCache {

    @CachePut
    public String update(String sql) {
        System.out.println("---update()执行了---" + sql);
        return "update sql语句";
    }

    @Cacheable
    public String save(String sql) {
        System.out.println("---save()执行了---" + sql);
        return "save sql语句";
    }

    @Cacheable
    public String select(String sql) {
        System.out.println("---select()执行了---" + sql);
        return "select sql语句";
    }

    @CacheEvict
    public String delete(String sql) {
        System.out.println("---delete()执行了---" + sql);
        return "delete sql语句";
    }

    @Cacheable
    public String select() {
        System.out.println("---没有参数的select()执行了---");
        return "空的select语句";
    }
}
