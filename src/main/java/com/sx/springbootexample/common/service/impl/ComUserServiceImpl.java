package com.sx.springbootexample.common.service.impl;

import com.sx.common.result.Result;
import com.sx.common.result.ResultUtil;
import com.sx.common.utils.JwtUtil;
import com.sx.springbootexample.common.baen.UserInfo;
import com.sx.springbootexample.common.service.ComUserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComUserServiceImpl implements ComUserService {
    Logger logger = LoggerFactory.getLogger(ComUserServiceImpl.class);


    long expirationTime = 1000 * 60 * 60 * 3; //token 有效期毫秒 3小时
    long refTime = 1000 * 60 * 30; //token 有效刷新时间  半小时

//    long expirationTime = 1000 * 60 * 2; //token 有效期  --test
//    long refTime = 1000 * 60; //token 有效刷新时间  --test

    @Override
    public String userSignIn(String userName, String passWord, String systemId) {
        // TODO: 2020/5/10 rbac用户认证权限获或判断账号密码是否正确,最后初始化userInfo对象生成token
        // 根据系统需要自定义用户各类信息对象
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("这里的用户名不是账号");
        userInfo.setAccountNumber(userName); //这是账号
        userInfo.setUserId("用户Id");
        userInfo.setDeptId("部门Id");
        userInfo.setCenterId("中心或单位Id");
        userInfo.setRolesId("用户角色Id");
        userInfo.setAuthId("权限级别Id");

        return createAccessToken(userInfo);
    }

    /**
     * 判断token是否过期未过期直接返回
     * 已过期的token判断是否在刷新时间范围内,未在刷新时间范围内需重新登录
     *
     * @param oldToken 过期token
     * @return 新的token
     */
    @Override
    public Result refreshAccessToken(String oldToken) {
        Claims claims = JwtUtil.parseJWT(oldToken);
        UserInfo userInfo = JwtUtil.parsJwt2Object(oldToken, UserInfo.class);

        Integer refreshCount = claims.get("refreshCount", Integer.class);
        long refreshTime = (long) claims.get("refreshTime");
        long nowTime = System.currentTimeMillis();

        if (JwtUtil.isExpired(oldToken)) {
            if (nowTime < refreshTime) {
                return ResultUtil.success(createAccessToken(userInfo, refreshCount + 1));
            } else {
                String msg = "refresh accessToken is Expired rest login";
                logger.info(msg);
                return ResultUtil.error(602, msg);
            }
        }
        logger.info("accessToken is Not Expired");
        return ResultUtil.success(oldToken);
    }

    /**
     * 通过用户信息生成token
     *
     * @param userInfo 用户信息
     * @return 返回token
     */
    @Override
    public String createAccessToken(UserInfo userInfo) {
        long nowTime = System.currentTimeMillis();
        long refreshTime = nowTime + expirationTime + refTime;
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("createTime", nowTime);
            put("refreshTime", refreshTime);
            put("refreshCount", 0);
        }};

        return JwtUtil.createJWT(map, userInfo, expirationTime);
    }

    @Override
    public String createAccessToken(UserInfo userInfo, int refreshCount) {
        long nowTime = System.currentTimeMillis();
        long refreshTime = nowTime + expirationTime + refTime;
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("createTime", nowTime);
            put("refreshTime", refreshTime);
            put("refreshCount", refreshCount);
        }};

        return JwtUtil.createJWT(map, userInfo, expirationTime);
    }

}
