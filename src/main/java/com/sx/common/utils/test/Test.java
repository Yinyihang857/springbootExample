package com.sx.common.utils.test;

import com.sx.springbootexample.common.baen.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @Autowired
    AppConfig appConfig;

//    @Value("${appconfig.serverName}")
//    private String serverNameAA;

    public void getConf() {
        System.out.println(appConfig.getUserName());
    }
}
