package com.sx.springbootexample.common.service;

import com.sx.common.result.Result;
import com.sx.springbootexample.common.baen.UserInfo;

public interface ComUserService {
    /**
     * 登录接口
     *
     * @param userName 账号
     * @param passWord 密码
     * @return 返回token
     */
    String userSignIn(String userName, String passWord, String systemId);

    /**
     * 刷新token接口
     *
     * @param oldToken 过期token
     * @return 返回新的token
     */
    Result refreshAccessToken(String oldToken);

    String createAccessToken(UserInfo userInfo);

    String createAccessToken(UserInfo userInfo, int refreshCount);
}
