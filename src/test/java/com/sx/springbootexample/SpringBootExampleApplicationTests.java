package com.sx.springbootexample;


import com.sx.common.utils.id.SnowFlake;
import com.sx.springbootexample.common.baen.AppConfig;
import com.sx.springbootexample.baen.User;
import com.sx.springbootexample.common.baen.UserInfo;
import com.sx.springbootexample.token.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        //30分钟
        long minutes = 1000 * 60 * 30;
        Date date = claims.getExpiration();

        long expirationTime = date.getTime();
        long newExpirationTime = date.getTime() + minutes;
        System.out.println(expirationTime + " 过期时间");
        System.out.println(newExpirationTime + " 延期时间");

//        claims.setExpiration()
        claims.setExpiration(new Date(newExpirationTime));

    }

    @Test
    void TokenTest2() {

        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ7XCJhY2NvdW50TnVtYmVyXCI6XCIxMTFcIixcImF1dGhJZFwiOlwi5p2D6ZmQ57qn5YirSWRcIixcImNlbnRlcklkXCI6XCLkuK3lv4PmiJbljZXkvY1JZFwiLFwiZGVwdElkXCI6XCLpg6jpl6hJZFwiLFwib3BlbklkXCI6XCJcIixcInBhc3NXb3JkXCI6XCJcIixcInJvbGVzSWRcIjpcIueUqOaIt-inkuiJsklkXCIsXCJzeXN0ZW1JZFwiOlwiXCIsXCJ1c2VyQXZhdGFyXCI6XCJcIixcInVzZXJJZFwiOlwi55So5oi3SWRcIixcInVzZXJOYW1lXCI6XCLov5nph4znmoTnlKjmiLflkI3kuI3mmK_otKblj7dcIn0iLCJuYmYiOjE1ODkxMjUyODAsImNyZWF0ZVRpbWUiOjE1ODkxMjUyODA4NjYsInJlZnJlc2hDb3VudCI6MCwicmVmcmVzaFRpbWUiOjE1ODkxMzc4ODA4NjYsImlzcyI6ImNvbS5zeC5zcHJpbmdCb290RXhhbXBsZSIsImV4cCI6MTU4OTEzNjA4MH0.TwF3qZp22VfkK0clh3dlJlUlA34ahn-crcl0FuKqefI";
        Boolean bool = JwtUtil.isExpired(token);

        Claims claims = JwtUtil.parseJWT(token);

        long createTime = (long) claims.get("createTime");
        long refreshTime = (long) claims.get("refreshTime");
        Integer refreshCount = claims.get("refreshCount", Integer.class);


        System.out.println(bool + " token是否过期");
        System.out.println(createTime + " token创建时间");
        System.out.println(refreshTime + " token多久可以刷新");
        System.out.println(refreshCount + " token刷新过几次token了");
        System.out.println(claims.getExpiration() + " token过期时间");
        System.out.println(claims.getSubject() + " token内容");

        UserInfo userInfo = JwtUtil.parsJwt2Object(token, UserInfo.class);
        System.out.println(userInfo.toString());


        //参数实例化为对象
//        System.out.println(claims.get("userInfo") + "OBj");

//        ObjectMapper mapper = new ObjectMapper();
//        UserInfo userInfo1 = mapper.convertValue(claims.get("userInfo"), UserInfo.class);

//        UserInfo userInfo1 = (UserInfo) claims.get("userInfo");
//        System.out.println(userInfo1.getDeptName());
//        System.out.println(userInfo1.toString());
//        System.out.println(userName);
//        System.out.println(claims.getExpiration().toString());


    }

}
