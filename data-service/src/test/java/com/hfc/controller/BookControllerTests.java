package com.hfc.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by user-hfc on 2018/8/20.
 *
 * @author user-hfc.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTests
{
    private static Logger LOGGER = LoggerFactory.getLogger(BookControllerTests.class);

    @Resource
    private BookController bookController;

    @Before
    public void init()
    {
        LOGGER.info("开始测试");
    }

    @Test
    public void test()
    {
        LOGGER.info(bookController.queryBookByPublishing("计算机"));
    }

    @After
    public void after()
    {
        LOGGER.info("测试结束");
        System.out.println("测试结束");
    }
}
