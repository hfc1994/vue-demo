package com.hfc.controller;

import com.alibaba.fastjson.JSON;
import com.hfc.dbOperations.service.ItemListService;
import com.hfc.entity.ItemList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@RestController
@RequestMapping("/itemlist")
public class ItemListController
{
    @Resource
    private ItemListService itemListService;

    @RequestMapping("/getItemListAll")
    public String getItemListAll()
    {
        List<ItemList> list = itemListService.queryItemListForAll();

        return JSON.toJSONString(list);
    }
}
