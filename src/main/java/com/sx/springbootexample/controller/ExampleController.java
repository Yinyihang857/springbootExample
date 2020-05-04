package com.sx.springbootexample.controller;


import com.sx.common.result.Result;
import com.sx.common.result.ResultUtil;
import com.sx.common.utils.id.SnowFlake;
import com.sx.springbootexample.baen.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/example")
@RestController
public class ExampleController {
    @Autowired
    AppConfig appConfig;


    /**
     * 配置文件例子
     */
    @RequestMapping("/conf")
    public String ConfTest() {

        String AppConfigStr = appConfig.toString();
        List<Object> userName = appConfig.getUserName();
        boolean dev = appConfig.getDev();
        int serverId = appConfig.getServerId();
        String serverName = appConfig.getServerName();
        Map<String, Object> map = appConfig.getMap();

        System.out.println(AppConfigStr);
        System.out.println(userName.get(0));
        System.out.println(dev);
        System.out.println(serverId);
        System.out.println(serverName);
        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));

        return "yes";
    }

    @RequestMapping("/result/success")
    public Result ResultSuccessExample() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        return ResultUtil.success(map);
    }

    @RequestMapping("/result/error")
    public Result ResultErrorExample() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        return ResultUtil.error(500, "操作失败", map);
    }

    @RequestMapping("/id")
    public Result IdExample() {
        Map<String, Object> map = new HashMap<>();
        SnowFlake snowFlake = new SnowFlake(appConfig.getServerId(), appConfig.getDatacenterId());

        for (int i = 0; i < 100; i++) {
            long id = snowFlake.nextId();
            map.put("key" + i, id);
        }

        return ResultUtil.success(map);
    }
}
