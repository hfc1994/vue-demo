package com.hfc.controller;

import com.alibaba.fastjson.JSON;
import com.hfc.dbOperations.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by user-hfc on 2018/8/20.
 *
 * @author user-hfc.
 */
@RestController
@ResponseBody
@RequestMapping(value = "/book", produces = "application/json;charset=utf-8")
public class BookController
{
    private static Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Resource
    private BookService bookService;

    @RequestMapping(value = "/queryByPublishing/{tag}",method = RequestMethod.GET)
    public String queryBookByPublishing(@PathVariable(name = "tag")String tag)
    {
        List<Map<String, String>> lm = bookService.queryBookByPublishing(tag);

        String strResult = null;
        if (null != lm)
        {
            strResult = JSON.toJSONString(lm);
        }
        else
        {
            strResult = "[]";
        }
        return strResult;
    }

    @RequestMapping(value = "/queryByPrices/{price}",method = RequestMethod.GET)
    public String queryBookByPrices(@PathVariable(name = "price")String price)
    {
        return "[]";
    }

    @RequestMapping(value = "/queryByScores/{score}",method = RequestMethod.GET)
    public String queryBookByScores(@PathVariable(name = "score")String score)
    {
        return "[]";
    }
}
