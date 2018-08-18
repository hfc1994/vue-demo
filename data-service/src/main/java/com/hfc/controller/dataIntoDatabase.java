package com.hfc.controller;

import com.hfc.dbOperations.service.FilmService;
import com.hfc.entity.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 *
 * 用于把本地的一个数据文件解析写到数据库中
 */
@RestController
@RequestMapping("/data")
public class dataIntoDatabase
{
    @Resource
    private FilmService filmService;

    private int id = 0;

    @RequestMapping("/insertFilm")
    public int insertIntoFilmTable()
    {
        try
        {
            id ++;
            Film film =new Film();
            film.setId(id);
            film.setOid("9999");
            film.setTitle("奇葩");
            film.setYear("2018");
            film.setType("未知");
            film.setStar(6.6);
            film.setDirectors("未知");
            film.setActors("未知");
            film.setCommenter(15);
            film.setDuration(110);
            film.setUrl("http://www.douban.com/s/lokjihuygnmjhgf");
            return filmService.insertFilmTable(film);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }
}
