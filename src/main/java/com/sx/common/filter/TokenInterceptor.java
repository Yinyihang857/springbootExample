package com.sx.common.filter;

import com.sx.common.result.Result;
import com.sx.common.utils.JsonUtils;
import com.sx.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TokenInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    private final static String ContentType = "application/json; charset=utf-8";
    //响应状态
    private final static Integer TokenIsNullCode = 601;
    private final static Integer TokenIsExpiredCode = 602;
    private final static Integer TokenIsFormatErrorCode = 603;
    private final static Integer TokenIsSignatureErrorCode = 604;
    private final static Integer RefreshTokenErrorCode = 605;

    //响应信息
    //全局异常捕获类里,同样会返回一些token解析过程中的异常信息
    private final static String TokenIsNullMsg = "accessToken is null please rest login";
    private final static String TokenIsExpiredMsg = "accessToken is Expired rest login";
    private final static String TokenIsFormatErrorMsg = "accessToken is Malformed access denied";
    private final static String TokenIsSignatureErrorMsg = "accessToken signature does not match locally computed signature";
    private final static String RefreshTokenErrorMsg = "accessToken is Expired but can be refreshed";

    /**
     * 写入Response
     *
     * @param response 响应对象
     */
    private void result(HttpServletResponse response, Integer code, String msg) throws IOException {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        response.setContentType(ContentType);
        PrintWriter pw = response.getWriter();
        pw.println(JsonUtils.toJSONString(result));
        pw.flush();
        pw.close();
    }

    /**
     * token 为空返回601
     *
     * @param response 响应对象
     */
    private void tokenIsNull(HttpServletResponse response) throws IOException {
        result(response, TokenIsNullCode, TokenIsNullMsg);
    }

    /**
     * token 过期重新登录返回602
     *
     * @param response 响应对象
     */
    private void tokenIsExpired(HttpServletResponse response) throws IOException {
        result(response, TokenIsExpiredCode, TokenIsExpiredMsg);
    }

    /**
     * token 格式不正确返回603
     *
     * @param response 响应对象
     */
    private void tokenIsFormatError(HttpServletResponse response) throws IOException {
        result(response, TokenIsFormatErrorCode, TokenIsFormatErrorMsg);
    }

    /**
     * token 超时但是可以刷新token返回605
     *
     * @param response 响应对象
     */
    private void RefreshTokenError(HttpServletResponse response) throws IOException {
        result(response, RefreshTokenErrorCode, RefreshTokenErrorMsg);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("accessToken");
        logger.debug("accessToken is = " + accessToken);

        //判断token是否存在
        if (accessToken == null || accessToken.equals("")) {
            logger.info(TokenIsNullMsg);
            tokenIsNull(response);
            return false;
        }

        //判断token格式及签名是否正确
        if (JwtUtil.parseJWT(accessToken) == null) {
            logger.info(TokenIsFormatErrorMsg);
            tokenIsFormatError(response);
            return false;
        }

        //判断token是否过期
        if (JwtUtil.isExpired(accessToken)) {
            Claims claims = JwtUtil.parseJWT(accessToken);
            long nowTimeMillis = System.currentTimeMillis();
            long refreshTime = (long) claims.get("refreshTime");

            //判断当前时间大于最后刷新时间则退出登录
            if (nowTimeMillis > refreshTime) {
                logger.info(TokenIsExpiredMsg);
                tokenIsExpired(response);
                return false;
            }

            //已超时但是还在个刷新范围内则通知客户端获取新的token重新发起请求
            logger.info(RefreshTokenErrorMsg);
            RefreshTokenError(response);
            return false;
        }

        return true;
    }
}
