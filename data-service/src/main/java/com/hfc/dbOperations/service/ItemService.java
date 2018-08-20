package com.hfc.dbOperations.service;

import com.hfc.dbOperations.dao.ItemListMapper;
import com.hfc.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@Service
public class ItemService
{
    private static Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ItemListMapper itemListMapper;

    public List<Item> queryAllItem()
    {
        List<Item> items = null;
        try
        {
            items = itemListMapper.queryAll();
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            items = null;
        }
        return items;
    }
}
