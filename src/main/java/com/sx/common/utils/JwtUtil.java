package com.sx.common.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * JWT token使用类
 *
 * @author yinyihang
 */
public class JwtUtil {

    private static final String SECRET = "com.sx.jsb.yyh.wdd.lz.fss";
    private static final String ISSUER = "com.sx.springBootExample";

    /**
     * 创建 Token
     *
     * @param claim     载荷信息
     * @param ttlmillis 超时时间 毫秒
     * @return Token
     */
    public static String createJWT(Map<String, Object> claim, long ttlmillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        String payLodData = JsonUtils.toJSONString(claim);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT") // 令牌类型
                .setClaims(claim) // 自定义生命
                .setIssuer(ISSUER) // 发行方
                .setSubject(payLodData) // 数据内容
                .signWith(signatureAlgorithm, signingKey); // 加密类型及密钥
        //添加Token过期时间
        if (ttlmillis >= 0) {
            long expMillis = nowMillis + ttlmillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

    //解析token
    public static Claims parseJWT(String jsonWebToken) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    /**
     * 检测token是否过期
     *
     * @param token token
     * @return boolean 过期返回true 未过期返回false
     */
    public static boolean isExpired(String token) {
        Date exp = parseJWT(token).getExpiration();
        return exp.before(new Date());
    }

    /**
     * 将token数据转为实例
     *
     * @param token token
     * @param t     实例
     * @return T token数据内容
     */
    public static <T> T parsJwt2Object(String token, Class<T> t) {
        String tokenData = parseJWT(token).getSubject();
        return JsonUtils.toObject(tokenData, t);
    }
}
