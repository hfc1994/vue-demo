package com.hfc.dbOperations.service;

import com.hfc.dbOperations.dao.FilmMapper;
import com.hfc.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@Service
public class FilmService
{
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
        System.out.println("一组" + films.size() + "条，写入成功");
        return iResult;
    }
}
