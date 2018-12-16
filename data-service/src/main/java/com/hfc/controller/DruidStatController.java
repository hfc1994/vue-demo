package com.hfc.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hfc on 2018/12/17.
 */
@RestController
public class DruidStatController {

    @GetMapping("/druid/stat")
    public Object druidStat() {
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
