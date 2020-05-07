package com.sx.common.filter;

import com.sx.common.result.Result;
import com.sx.common.utils.JsonUtils;
import com.sx.common.utils.JwtUtil;
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
    private final static Integer TokenIsNullCode = 501;
    private final static Integer TokenIsExpiredCode = 502;
    private final static Integer TokenIsFormatErrorCode = 503;
    private final static Integer TokenIsSignatureErrorCode = 504;

    //响应信息
    //全局异常捕获类里,同样会返回一些token解析过程中的异常信息
    private final static String TokenIsNullMsg = "accessToken is null please rest login";
    private final static String TokenIsExpiredMsg = "accessToken is Expired please Renew";
    private final static String TokenIsFormatErrorMsg = "accessToken is Malformed access denied";
    private final static String TokenIsSignatureErrorMsg = "accessToken signature does not match locally computed signature";

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
     * token 为空返回501
     *
     * @param response 响应对象
     */
    private void tokenIsNull(HttpServletResponse response) throws IOException {
        result(response, TokenIsNullCode, TokenIsNullMsg);
    }

    /**
     * token 超时返回502
     *
     * @param response 响应对象
     */
    private void tokenIsExpired(HttpServletResponse response) throws IOException {
        result(response, TokenIsExpiredCode, TokenIsExpiredMsg);
    }

    /**
     * token 格式不正确返回502
     *
     * @param response 响应对象
     */
    private void tokenIsFormatError(HttpServletResponse response) throws IOException {
        result(response, TokenIsFormatErrorCode, TokenIsFormatErrorMsg);
    }

    @Override
    //请求进来执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("accessToken");
        logger.debug("accessToken is = " + accessToken);

        if (accessToken == null || accessToken.equals("")) {
            logger.info(TokenIsNullMsg);
            tokenIsNull(response);
            return false;
        }

        if (JwtUtil.parseJWT(accessToken) == null) {
            logger.info(TokenIsFormatErrorMsg);
            tokenIsFormatError(response);
            return false;
        }

        if (JwtUtil.isExpired(accessToken)) {

        }

        return true;
    }
}
