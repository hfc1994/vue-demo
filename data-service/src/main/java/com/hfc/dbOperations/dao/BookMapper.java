package com.hfc.dbOperations.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by user-hfc on 2018/8/20.
 *
 * @author user-hfc.
 */
@Mapper
public interface BookMapper
{
    @Select("select publishing,count(id) as total from book where title like '%${tag}%' group by publishing")
    @ResultType(List.class)
    List<Map<String, String>> queryBookByPublishing(@Param("tag")String tag);
}
