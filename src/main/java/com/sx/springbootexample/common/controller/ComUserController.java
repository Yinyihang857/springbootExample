package com.sx.springbootexample.common.controller;


import com.sx.common.result.Result;
import com.sx.common.result.ResultUtil;
import com.sx.springbootexample.common.baen.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;


/**
 * 系统登录类
 * 2020-05-08
 */

@RequestMapping("/common")
@RestController
public class ComUserController {
    Logger logger = LoggerFactory.getLogger(ComUserController.class);

    @RequestMapping(value = "/users/sign_in", method = RequestMethod.POST)
    public Result UserLogin(@NotNull @RequestParam("userName") String userName) {

        System.out.println(userName);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/users/sign_in_test", method = RequestMethod.POST)
    public Result UserLoginTest(UserInfo userInfo) {

        System.out.println(userInfo.getUserName());
        return ResultUtil.success();
    }
}
