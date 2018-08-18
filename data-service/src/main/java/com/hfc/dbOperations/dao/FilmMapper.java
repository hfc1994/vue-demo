package com.hfc.dbOperations.dao;

import com.hfc.entity.Film;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@Mapper
public interface FilmMapper
{
    @Insert("insert into film(id, oid, title, year, type, star, directors, " +
            "actors, commenter, duration, url) values (#{id}, #{oid}, #{title}, " +
            "#{year}, #{type}, #{star}, #{directors}, #{actors}, #{commenter}, #{duration}, #{url})")
    int insertFilm(Film film);
}
