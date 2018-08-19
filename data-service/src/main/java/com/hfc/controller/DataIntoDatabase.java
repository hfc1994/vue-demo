package com.hfc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hfc.dbOperations.service.FilmService;
import com.hfc.entity.Film;
import com.hfc.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 *
 * 用于把本地的一个数据文件解析写到数据库中
 */
@RestController
@RequestMapping("/data")
public class DataIntoDatabase
{
    @Resource
    private FilmService filmService;

    /**
     * 用于吧Film.json数据解析出来然后写入数据库
     * @return 0表示失败，1表示成功
     */
    @RequestMapping("/insertFilm")
    public int insertIntoFilmTable()
    {
        FileReader fr = null;
        BufferedReader reader = null;

        //成功写入的条数和失败的条数
        int success = 0;
        int fail = 0;
        try
        {
            //数据源文件
            String jsonUrl = "G:\\Java_idea\\Film.json";

            fr = new FileReader(new File(jsonUrl));
            reader = new BufferedReader(fr);

            String content = null;
            JSONObject json = null;
            Film film = null;
            List<Film> films = new ArrayList<>(10);

            int count = 0;
            while (true)
            {
                content = reader.readLine();

                //如果读取到的数据为空，那么说明数据读完了，需要结束了
                if (!StringUtils.isEmpty(content))
                {
                    json = JSON.parseObject(content);
                    film = new Film();
                    film.setOid(json.getJSONObject("_id").getString("$oid").trim());
                    film.setTitle(json.getString("title").trim());
                    film.setYear(json.getString("year").trim());
                    film.setType(json.getString("type").trim());
                    film.setStar(json.getDouble("star"));
                    film.setDirectors(json.getString("director").trim());
                    film.setActors(json.getString("actor").trim());
                    film.setCommenter(json.getIntValue("pp"));
                    film.setDuration(json.getString("time"));
                    film.setUrl(json.getString("film_page").trim());

                    //达到10条就直接批量写数据库了
                    count++;
                    if (count >= 10)
                    {
                        count = 0;

                        //由于数据源格式的问题，数据的类型和长度没法预估
                        //因此如下保证尽量多的数据写入数据库
                        try
                        {
                            filmService.batchInsertFilmTable(films);
                            success += films.size();
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.getMessage());

                            for (Film f : films)
                            {
                                try
                                {
                                    filmService.insertFilmTable(f);
                                    success++;
                                }
                                catch (Exception ee)
                                {
                                    System.out.println("此处失败了一条");
                                    fail++;
                                }
                            }
                        }

                        films.clear();
                    }

                    films.add(film);
                }
                else
                {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            releaseResource(fr);
            releaseResource(reader);
        }

        System.out.println("成功：" + success + ",失败：" + fail);
        return 1;
    }

    private void releaseResource(Closeable toClose)
    {
        if (null != toClose)
        {
            try
            {
                toClose.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
