package com.hfc.dbOperations.service;

import com.hfc.dbOperations.dao.FilmMapper;
import com.hfc.entity.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
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
        return filmMapper.insertFilm(film);
    }

    public int batchInsertFilmTable(List<Film> films)
    {
        int iResult = filmMapper.batchInsertFilm(films);
        LOGGER.info("一组" + films.size() + "条，写入成功");
        return iResult;
    }

    public List<Map<String, Integer>> queryFilmGroupByYear()
    {
        List<Map<String, Integer>> lm = filmMapper.queryFilmGroupByYear();
        return lm;
    }
}
