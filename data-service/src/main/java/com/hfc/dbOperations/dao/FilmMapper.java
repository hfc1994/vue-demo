package com.hfc.dbOperations.dao;

import com.hfc.entity.Film;
import org.apache.ibatis.annotations.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@Mapper
public interface FilmMapper
{
    /**
     * 用于向数据库写入单条film数据的接口
     * @param film 需要写入的单条film数据
     * @return 1/0，成功/失败
     */
    @Insert("insert into film(id, oid, title, year, type, star, directors, " +
            "actors, commenter, duration, url) values (#{id}, #{oid}, #{title}, " +
            "#{year}, #{type}, #{star}, #{directors}, #{actors}, #{commenter}, #{duration}, #{url})")
    int insertFilm(Film film);

    /**
     * 用于向数据库批量写入film数据的接口
     * @param films 需要批量写入的film数据
     * @return 1/0，成功/失败
     */
    @InsertProvider(type = FilmProvider.class, method = "batchInsert")
    int batchInsertFilm(List<Film> films);

    /**
     * 根据年份（year）来分组，查询各个年份的电影数量
     * @return 年份和对应年份的数量
     */
    @Select("select year,count(id) as total from film group by year")
    @ResultType(List.class)
    List<Map<String, Long>> queryFilmGroupByYear();

    @Select("select count(id) as total from film where type like '%${tag}%'")
    Map<String, Long> queryFilmByType(@Param("tag")String tag);

    @Select("select count(id) as total from film where star between ${begin} and ${end}")
    Map<String, String> queryFilmByStar(@Param("begin")String begin, @Param("end")String end);

    class FilmProvider
    {
        public String batchInsert(Map map)
        {
            List<Film> films = (List<Film>) map.get("list");
            StringBuilder sb = new StringBuilder("insert into film(id, oid, title, year, type, star, directors, " +
                    "actors, commenter, duration, url) values ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].id}, #'{'list[{0}].oid}, #'{'list[{0}].title}," +
                    " #'{'list[{0}].year}, #'{'list[{0}].type}, #'{'list[{0}].star}, #'{'list[{0}].directors}, #'{'list[{0}].actors}," +
                    " #'{'list[{0}].commenter}, #'{'list[{0}].duration}, #'{'list[{0}].url})");

            for (int i=0; i<films.size(); i++)
            {
                sb.append(mf.format(new Object[] {i}));
                if (i < films.size() - 1)
                {
                    sb.append(",");
                }
            }

//            System.out.println(sb.toString());
            return sb.toString();
        }
    }
}
