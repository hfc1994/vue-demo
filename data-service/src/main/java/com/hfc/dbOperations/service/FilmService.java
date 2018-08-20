package com.hfc.dbOperations.service;

import com.hfc.dbOperations.dao.FilmMapper;
import com.hfc.entity.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 *
 * int类型返回值里
 * 1表示成功
 * 0表示失败
 * -1表示异常
 */
@Service
public class FilmService
{
    private static Logger LOGGER = LoggerFactory.getLogger(FilmService.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private FilmMapper filmMapper;

    public int insertFilmTable(Film film)
    {
        int iResult;
        try
        {
            iResult = filmMapper.insertFilm(film);
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            iResult = -1;
        }

        return iResult;
    }

    public int batchInsertFilmTable(List<Film> films)
    {
        int iResult;
        try
        {
            iResult= filmMapper.batchInsertFilm(films);
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            iResult = -1;
        }

        return iResult;
    }

    /**
     * 根据年份来查询当年的电影数量
     * @param numLimit 当某年电影数量少于这个指定阈值时被归为others
     * @return
     */
    public List<Map<String, String>> queryFilmGroupByYear(int numLimit)
    {
        List<Map<String, String>> result = null;
        try
        {
            List<Map<String, Long>> lm = filmMapper.queryFilmGroupByYear();

            result = new ArrayList<>(lm.size());

            //小于阈值的电影数量
            int iNumLimit = 0;
            Map<String, String> tmpMap = null;
            for (Map<String, Long> map : lm)
            {
                if (map.get("total") <= numLimit)
                {
                    iNumLimit ++;
                }
                else
                {
                    tmpMap = new HashMap<>(1);
                    tmpMap.put("year", String.valueOf(map.get("year")));
                    tmpMap.put("total", String.valueOf(map.get("total")));
                    result.add(tmpMap);
                }
            }

            if (iNumLimit > 0)
            {
                Map<String, String> tmp = new HashMap<>(1);
                tmp.put("year","others");
                tmp.put("total", String.valueOf(iNumLimit));
                result.add(tmp);
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            result = null;
        }
        return result;
    }

    /**
     * 根据Type查询对应类型的电影数量
     * 这里默认做常见的12种类型
     * @return 各类型电影数量
     */
    public List<Map<String, String>> queryFilmByType()
    {
        //初始化赋初始值
        List<String> tags = new ArrayList<String>(12)
        {
            {add("动作");}
            {add("喜剧");}
            {add("爱情");}
            {add("古装");}
            {add("奇幻");}
            {add("恐怖");}
            {add("科幻");}
            {add("冒险");}
            {add("犯罪");}
            {add("战争");}
            {add("灾难");}
            {add("悬疑");}
        };
        List<Map<String, String>> result = new ArrayList<>(12);

        Map<String, String> map = new HashMap<>(12);
        for (String tag : tags)
        {
            //哪种出异常就跳过哪种
            try
            {
                Map<String, Long> tmp = filmMapper.queryFilmByType(tag);
                map.put(tag, String.valueOf(tmp.get("total")));
                result.add(map);
            }
            catch (Exception e)
            {
                LOGGER.error(e.getMessage());
            }
        }

        return result;
    }
}
