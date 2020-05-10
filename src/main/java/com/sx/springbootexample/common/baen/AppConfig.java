package com.sx.springbootexample.common.baen;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *      prefix = "appconfig"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 *  @ConfigurationProperties(prefix = "appconfig")默认从全局配置文件中获取值；
 *
 */
//@PropertySource(value = {"classpath:person.properties"})
@Component
//@Configuration
@ConfigurationProperties(prefix = "appconfig")
public class AppConfig {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
     * <bean/>
     */

    //获取数组类型的配置
    private List<Object> userName;
    //获取Int类型配置
    private Integer serverId;
    private Integer datacenterId;
    //获取字符串类型配置
    private String serverName;
    //获取bool类型配置
    private Boolean dev;
    //map类型配置
    private Map<String, Object> map;

    @Override
    public String toString() {
        return "AppConfig{" +
                "userName=" + userName +
                ", serverId=" + serverId +
                ", datacenterId=" + datacenterId +
                ", serverName='" + serverName + '\'' +
                ", dev=" + dev +
                ", map=" + map +
                '}';
    }

    public Integer getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(Integer datacenterId) {
        this.datacenterId = datacenterId;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Object> getUserName() {
        return userName;
    }

    public void setUserName(List<Object> userName) {
        this.userName = userName;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Boolean getDev() {
        return dev;
    }

    public void setDev(Boolean dev) {
        this.dev = dev;
    }
}
