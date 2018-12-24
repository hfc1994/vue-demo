package com.hfc.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by user-hfc on 2018/12/15.
 */
@RunWith(SpringRunner.class)
@EnableCaching
@SpringBootTest
public class DouBanCacheTest {

    @Autowired
    private DatabaseCache cache;

    private String sql = "select * from douban";

    @Test
    public void test() throws InterruptedException {
        String result = null;
        result = cache.testSelect(sql);
        System.out.println(result);

        result = cache.testUpdate(sql);
        System.out.println(result);

        result = cache.testSelect(sql);
        System.out.println(result);

        result = cache.testDelete(sql);
        System.out.println(result);

        result = cache.testSelect(sql);
        System.out.println(result);

        result = cache.testUpdate(sql);
        System.out.println(result);

        result = cache.testSelect(sql);
        System.out.println(result);

        result = cache.testSelect(sql);
        System.out.println(result);

        // 等待缓存过期
        TimeUnit.MINUTES.sleep(3);

        result = cache.testSelect(sql);
        System.out.println(result);
    }

    @Test
    public void testKeyGenerator() {
        String result = cache.testSelect();
        System.out.println(result);

        result = cache.testSelect();
        System.out.println(result);
        result = cache.testSelect();
        System.out.println(result);
        result = cache.testSelect();
        System.out.println(result);
    }
}
