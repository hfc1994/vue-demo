package com.hfc.dbOperations.dao;

import com.hfc.entity.ItemList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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
    List<ItemList> queryAll ();
}
