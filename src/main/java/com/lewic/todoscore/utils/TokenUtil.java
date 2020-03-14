package com.lewic.todoscore.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT认证
 * 配置可参考 https://www.jianshu.com/p/e88d3f8151db
 *
 * @author lewic
 * @since 2020/3/7 16:35
 */
@Slf4j
public class TokenUtil {

    /**
     * jwt秘钥盐
     */
    public static final String TOKEN_SECRET = "token123";

    /**
     * 签名生成
     */
    public static String sign(User user) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer(Constants.JWT_ISSUER)
                    // todo 这块应该塞id
                    .withClaim(Constants.JWT_USERNAME, user.getUsername())
                    .withClaim(Constants.JWT_USERNAME, user.getUsername())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     */
    public static boolean verify(String token, User user) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword()))
                    .withIssuer(Constants.JWT_ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("username: " + jwt.getClaim(Constants.JWT_USERNAME).asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取携带信息
     */
    public static String getJwtUsername(String token) {
        try {
            return JWT.decode(token).getClaim(Constants.JWT_USERNAME).asString();
        } catch (Exception e) {
            return null;
        }
    }

}