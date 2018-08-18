package com.hfc.dbOperations.service;

import com.hfc.dbOperations.dao.FilmMapper;
import com.hfc.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        int iResult = filmMapper.insertFilm(film);
        System.out.println("iResult==" + iResult);
        return iResult;
    }
}
