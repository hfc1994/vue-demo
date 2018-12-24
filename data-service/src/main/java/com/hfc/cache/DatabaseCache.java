package com.hfc.cache;

import com.hfc.dbOperations.dao.BookMapper;
import com.hfc.dbOperations.dao.FilmMapper;
import com.hfc.dbOperations.dao.ItemListMapper;
import com.hfc.entity.Item;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public String testUpdate(String sql) {
        System.out.println("---testUpdate()执行了---" + sql);
        return "update sql语句";
    }

    @Cacheable
    public String testSave(String sql) {
        System.out.println("---testSave()执行了---" + sql);
        return "save sql语句";
    }

    @Cacheable
    public String testSelect(String sql) {
        System.out.println("---testSelect()执行了---" + sql);
        return "select sql语句";
    }

    @CacheEvict
    public String testDelete(String sql) {
        System.out.println("---testDelete()执行了---" + sql);
        return "delete sql语句";
    }

    @Cacheable
    public String testSelect() {
        System.out.println("---testSelect()执行了---");
        return "空的select语句";
    }
}
