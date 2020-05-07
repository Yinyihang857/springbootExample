package com.sx.common.utils.test;

import com.sx.springbootexample.baen.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
