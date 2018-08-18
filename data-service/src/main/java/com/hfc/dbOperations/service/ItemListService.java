package com.hfc.dbOperations.service;

import com.hfc.dbOperations.dao.ItemListMapper;
import com.hfc.entity.ItemList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@Service
public class ItemListService
{
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ItemListMapper itemListMapper;

    public List<ItemList> queryItemListForAll()
    {
        return itemListMapper.queryAll();
    }
}
