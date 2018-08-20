package com.hfc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hfc.dbOperations.service.ItemService;
import com.hfc.entity.Item;
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
 *
 * 前端标签列表的结构类似如下，只有子节点可以点击跳转
 * 站点列表（一级节点）
 * ---豆瓣（二级节点）
 * ------豆瓣电影（三级节点）
 * ------豆瓣图书
 * ------豆瓣音乐
 * ---猫眼
 * ------猫眼电影
 */
@RestController
@ResponseBody
@RequestMapping("/item")
public class ItemController
{
    @Resource
    private ItemService itemService;

    @RequestMapping(value = "/getItems", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getItemListAll()
    {
        List<Item> list = itemService.queryAllItem();
        String content = "[]";

        // 前端需要接收这种格式的数据来显示列表
        // [{"name":"","id":"","children":[{"name":"","id":"","children":[{"name":"","id":""},{}]},{}]}]
        if (null != list)
        {
            JSONObject json = new JSONObject();
            json.put("name", "站点列表");
            json.put("id", "0");

            JSONArray jArray = new JSONArray();
            JSONObject jsonTmp = null;
            for (Item item : list)
            {
                if ("0".equals(item.getItem_pre()))
                {
                    jsonTmp = new JSONObject();
                    jsonTmp.put("name", item.getItem());
                    jsonTmp.put("type", item.getItem_id());
                    jsonTmp.put("id", item.getId());
                    setChildrenNode(jsonTmp, item.getItem_id(), list);
                    jArray.add(jsonTmp);
                }
            }
            json.put("children", jArray);

            content = "[" + json.toJSONString() + "]";
        }

//        System.out.println("content = " + content);

        return content;
    }

    /**
     * 二级父节点下面可能会有多个三级子节点
     * 所以需要根据三级子节点的item_pre来判断是否归属于当前二级节点
     * @param parent 当前二级节点
     * @param itemId 三级节点所需对应的item_pre，也即是二级节点的id
     * @param list 数据源
     */
    private void setChildrenNode(JSONObject parent, String itemId, List<Item> list)
    {
        JSONArray jChildArray = new JSONArray();
        for (Item childItem : list)
        {
            if (itemId.equals(childItem.getItem_pre()))
            {
                JSONObject childJson = new JSONObject();
                childJson.put("name", childItem.getItem());
                childJson.put("type", childItem.getItem_id());
                childJson.put("id", childItem.getId());
                jChildArray.add(childJson);
            }
        }
        parent.put("children", jChildArray);
    }
}
