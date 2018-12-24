package com.hfc.dbOperations.service;

import com.hfc.dbOperations.dao.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by user-hfc on 2018/8/20.
 *
 * @author user-hfc.
 */
@CacheConfig(cacheNames = "database")
@Service
public class BookService
{
    private static Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private BookMapper bookMapper;

    /**
     * 根据关键词来查询各出版社分别出版了多少类似的数据
     * @param tag 关键词
     * @return 各出版社出版的数量
     */
    @Cacheable
    public List<Map<String, String>> queryBookByPublishing(String tag)
    {
        List<Map<String, String>> lm = null;
        try
        {
            lm = bookMapper.queryBookByPublishing(tag);
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            lm = null;
        }

        return lm;
    }
}
