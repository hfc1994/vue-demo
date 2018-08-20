package com.hfc.dbOperations.dao;

import com.hfc.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@Mapper
public interface ItemListMapper
{
    @Select("select * from item_list")
    List<Item> queryAll ();
}
