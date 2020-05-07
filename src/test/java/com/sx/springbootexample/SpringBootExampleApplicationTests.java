package com.sx.springbootexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sx.common.utils.JsonUtils;
import com.sx.common.utils.id.SnowFlake;
import com.sx.springbootexample.baen.AppConfig;
import com.sx.springbootexample.baen.User;
import com.sx.springbootexample.token.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringBootExampleApplicationTests {
    Logger logger = LoggerFactory.getLogger(SpringBootExampleApplicationTests.class);

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
    void TokenTest() {
        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("deptName", "技术服务部");

        Map<String, Object> map = new HashMap<>();
        map.put("userId", "01");
        map.put("userName", "yinyihang");
        map.put("userInfo", userInfo);

        //毫秒
        String token = JwtUtil.createJWT(map, 1000 * 60 * 60);
        System.out.println(token);

        //是否过期
        Boolean bool = JwtUtil.isExpired(token);
        System.out.println(bool + " 是否过期");

        //创建时间
        Claims claims = JwtUtil.parseJWT(token);
        System.out.println(claims.getNotBefore() + " 创建时间");

        //过期时间
        System.out.println(claims.getExpiration() + " 过期时间");

        //获取token数据
        User user = JwtUtil.parsJwt2Object(token, User.class);
        System.out.println(user.toString() + " 获取token数据");

        //        long nowMillis = System.currentTimeMillis();
        //        Date now = new Date(nowMillis);
        //30分钟
        long minutes = 1000 * 60 * 30;
        Date date = claims.getExpiration();

        System.out.println(date.getTime());
//        claims.setExpiration()

    }

    @Test
    void TokenTest2() {

//        UserInfo userInfo = new UserInfo();

        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySW5mbyI6eyJkZXB0TmFtZSI6IuaKgOacr-acjeWKoemDqCJ9LCJuYmYiOjE1ODg3NzY1OTAsImlzcyI6ImNvbS5zeCIsInVzZXJOYW1lIjoieWlueWloYW5nIiwiZXhwIjoxNTg4Nzc2NTkxLCJ1c2VySWQiOiIwMSJ9.2yo90QWhzuTlPgqbq81M3i_AD23Vj13SJJdak8yWAh8";
        Boolean bool = JwtUtil.isExpired(token);

        Claims claims = JwtUtil.parseJWT(token);

        String userName = claims.get("userName", String.class);


//        ObjectMapper mapper = new ObjectMapper();
//        UserInfo userInfo1 = mapper.convertValue(claims.get("userInfo"), UserInfo.class);

        //参数实例化为对象
        System.out.println(claims.get("userInfo") + "OBj");


//        UserInfo userInfo1 = (UserInfo) claims.get("userInfo");
//        System.out.println(userInfo1.getDeptName());
//        System.out.println(userInfo1.toString());
//        System.out.println(userName);
//        System.out.println(claims.getExpiration().toString());

        System.out.println(bool);

    }

}
