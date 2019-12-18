package com.ctgu.contributionsystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 16:52
 * @ClassName JwtUtil
 * @Version 1.0.0
 */
@Slf4j
public class JwtUtil {
    //token 过期时间
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * @Author wh
     * @Description 验证token是否正确
     * @Date 2019/12/18 16:53
     * @Param [token, phoneNumber, secret]
     * @return boolean
     **/

    public static boolean verify(String token, String phoneNumber, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("phoneNumber", phoneNumber)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("登录验证成功!");
            return true;
        } catch (Exception exception) {
            log.error("JwtUtil登录验证失败!");
            return false;
        }
    }

    /**
     * @Author wh
     * @Description 获得token中的信息无需secret解密也能获得
     * @Date 2019/12/18 16:54
     * @Param [token]
     * @return java.lang.String token中包含的用户名
     **/

    public static String getPhoneNumber (String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("phoneNumber").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * @Author wh
     * @Description 生成签名,5min后过期
     * @Date 2019/12/18 16:54
     * @Param [phoneNumber, secret]
     * @return java.lang.String 加密的token
     **/
    public static String sign(String phoneNumber, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带phoneNumber信息
        return JWT.create()
                .withClaim("phoneNumber", phoneNumber)
                .withExpiresAt(date)
                .sign(algorithm);

    }


}
