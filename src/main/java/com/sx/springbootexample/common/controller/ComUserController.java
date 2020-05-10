package com.sx.springbootexample.common.controller;


import com.sx.common.result.Result;
import com.sx.common.result.ResultUtil;
import com.sx.common.utils.JwtUtil;
import com.sx.springbootexample.common.baen.UserInfo;
import com.sx.springbootexample.common.service.ComUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * 系统公共类
 * 2020-05-08
 */

@Validated
@RequestMapping("/common")
@RestController
public class ComUserController {
    Logger logger = LoggerFactory.getLogger(ComUserController.class);

    @Autowired
    ComUserService comUserService;

    /**
     * 登录接口
     * 单个参数校验 @NotBlank(message = "错误提示")
     * 请求参数用debug打印
     *
     * @param userName 账号
     * @param passWord 密码
     * @return accessToken
     */
    @RequestMapping(value = "/users/sign_in", method = RequestMethod.POST)
    public Result UserLogin(@NotBlank(message = "账号为空") String userName, @NotBlank(message = "密码为空") String passWord,
                            @NotBlank(message = "系统Id为空") String systemId) {
        String signInMsg = String.format("登录请求, 账号:%s, 密码:%s, 登录系统Id为:%s", userName, passWord, systemId);
        logger.debug(signInMsg);

        String accessToken = comUserService.userSignIn(userName, passWord, systemId);
        if (accessToken == null) {
            String errorMsg = String.format("账号:%s 登录失败", userName);
            logger.error(errorMsg);
            return ResultUtil.error(500, errorMsg);
        }

        return ResultUtil.success(accessToken);
    }

    /**
     * 通过旧token换取新token
     *
     * @param token 旧token
     * @return 新token
     */
    @RequestMapping(value = "/users/refresh/accessToken", method = RequestMethod.POST)
    public Result RefreshAccessToken(@NotBlank(message = "accessToken为空") String token) {
        logger.debug("刷新accessToken旧的token为:" + token);
        return comUserService.refreshAccessToken(token);
    }

    /**
     * token超时测试
     *
     * @param request 实例化对象
     * @return userInfo
     */
    @RequestMapping(value = "/users/token_timeout_test", method = RequestMethod.POST)
    public Result TokenTimeOutTest(HttpServletRequest request) {
        System.out.println(request.getHeader("accessToken"));
        //获取用户信息
        UserInfo userInfo = JwtUtil.parsJwt2Object(request.getHeader("accessToken"), UserInfo.class);
        System.out.println(userInfo.toString());
        return ResultUtil.success(userInfo);
    }
}
