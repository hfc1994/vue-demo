package com.hfc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hfc on 2018/11/5.
 *
 * 模拟的假数据
 */
@RestController
@ResponseBody
@RequestMapping("/mock")
public class MockController
{
    private static String[] provinces = {"浙江","北京","上海","安徽","江苏","福建","广东","广西","云南"};

    @RequestMapping(value = "/getMockData", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getMockData()
    {
        JSONArray jArray = new JSONArray();
        for (String province : provinces)
        {
            JSONObject json = new JSONObject();
            json.put("省份", province);
            json.put("数量", String.valueOf((int)(Math.random() * 100 + 1)));
            jArray.add(json);
        }

        JSONObject ret = new JSONObject();
        ret.put("key", "省份,数量");
        ret.put("value", jArray);

        return ret.toJSONString();
    }
}
