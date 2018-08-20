package com.hfc.controller;

import com.alibaba.fastjson.JSON;
import com.hfc.dbOperations.service.FilmService;
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
@RequestMapping(value = "/film",produces = "application/json;charset=utf-8")
public class FilmController
{
    private static Logger LOGGER = LoggerFactory.getLogger(FilmController.class);

    @Resource
    private FilmService filmService;

    @RequestMapping(value = "/queryGroupByYear/{numLimit}", method = RequestMethod.GET)
    public String queryFilmGroupByYear(@PathVariable(name="numLimit")int numLimit)
    {
        List<Map<String, String>> map = filmService.queryFilmGroupByYear(numLimit);

        String strMap = null;
        if (null == map)
        {
            strMap = "[]";
        }
        else
        {
            strMap = JSON.toJSONString(map);
        }

//        LOGGER.info(strMap);

        return strMap;
    }

    @RequestMapping(value = "/queryByType", method = RequestMethod.GET)
    public String queryFilmByType()
    {
        List<Map<String, String>> lm = filmService.queryFilmByType();

        String strList = null;
        if (lm.size() == 0)
        {
            strList = "[]";
        }
        else
        {
            strList = JSON.toJSONString(lm);
        }

        return strList;
    }

    @RequestMapping(value = "/queryByStar", method = RequestMethod.GET)
    public String queryFilmByStar()
    {
        List<Map<String, String>> lm = filmService.queryFilmByStar();

        String strList = null;
        if (lm.size() == 0)
        {
            strList = "[]";
        }
        else
        {
            strList = JSON.toJSONString(lm);
        }

        return strList;
    }
}
