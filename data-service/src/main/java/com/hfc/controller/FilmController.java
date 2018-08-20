package com.hfc.controller;

import com.hfc.dbOperations.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/film")
public class FilmController
{
    private static Logger LOGGER = LoggerFactory.getLogger(FilmController.class);

    @Resource
    private FilmService filmService;

    @RequestMapping(value = "/queryGroupByYear", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public void queryFilmGroupByYear()
    {
        LOGGER.info("进入queryFilmGroupByYear");
        List<Map<String, Integer>> lm = filmService.queryFilmGroupByYear();

        System.out.println(lm.get(0).toString());
    }
}
