package com.sx.springbootexample;

import com.sx.common.utils.JsonUtils;
import com.sx.common.utils.id.SnowFlake;
import com.sx.springbootexample.baen.AppConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringBootExampleApplicationTests {

    @Autowired
    private AppConfig appConfig;

    @Test
    void SnowFlakeTest() {
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            SnowFlake snowFlake = new SnowFlake(appConfig.getServerId(), appConfig.getDatacenterId());
            long id = snowFlake.nextId();
            map.put("key" + i, id);
        }

        System.out.println(map);
    }

    @Test
    void LoggerTest(){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("这是info日志");
        logger.debug("这是debug日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }

}
