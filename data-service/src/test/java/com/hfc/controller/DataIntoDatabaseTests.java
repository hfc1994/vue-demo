package com.hfc.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by user-hfc on 2018/8/19.
 *
 * @author user-hfc.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class DataIntoDatabaseTests
{
    private static Logger LOGGER = LoggerFactory.getLogger(DataIntoDatabaseTests.class);

    @Resource
    private DataIntoDatabase did;

    @Before
    public void init()
    {
        System.out.println("开始测试---");
    }

    @Test
    public void test()
    {
//        DataIntoDatabase did = new DataIntoDatabase();
//        int iResult = did.insertIntoFilmTable();
//        System.out.println("写入结果：" + iResult);
        LOGGER.debug("测试写入日志");
        LOGGER.info("测试写入日志");
        LOGGER.error("测试写入日志");
    }

    @After
    public void after()
    {
        System.out.println("测试结束---");
    }
}
