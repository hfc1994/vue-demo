package com.hfc.controller;

import com.alibaba.fastjson.JSON;
import com.hfc.dbOperations.service.ItemListService;
import com.hfc.entity.ItemList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@RestController
@ResponseBody
@RequestMapping("/itemList")
public class ItemListController
{
    @Resource
    private ItemListService itemListService;

    @RequestMapping(value = "/getItemListAll", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getItemListAll()
    {
        List<ItemList> list = itemListService.queryItemListForAll();

        return JSON.toJSONString(list);
    }
}
