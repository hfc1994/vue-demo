package com.hfc.dbOperations.dao;

import com.hfc.entity.Film;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

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
    @Insert("insert into film(id, oid, title, year, type, star, directors, " +
            "actors, commenter, duration, url) values (#{id}, #{oid}, #{title}, " +
            "#{year}, #{type}, #{star}, #{directors}, #{actors}, #{commenter}, #{duration}, #{url})")
    int insertFilm(Film film);

    @InsertProvider(type = FilmProvider.class, method = "batchInsert")
    int batchInsertFilm(List<Film> films);


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

            System.out.println(sb.toString());
            return sb.toString();
        }
    }
}
