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
 * Created by hfc on 2018/8/20.
 *
 * @author hfc.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmControllerTests
{
    private static Logger LOGGER = LoggerFactory.getLogger(FilmControllerTests.class);

    @Resource
    private FilmController filmController;

    @Before
    public void init()
    {
        LOGGER.info("开始测试");
    }

    @Test
    public void test()
    {
        LOGGER.info(filmController.queryFilmByType());
    }

    @After
    public void after()
    {
        LOGGER.info("测试结束");
        System.out.println("测试结束");
    }
}
