package com.example.learnmalldemo.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * JwtToken生成工具
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-05-26 15:20
 */
@Slf4j
@Component
public class JwtTokenUtils implements ApplicationContextAware {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    private static ApplicationContext ac;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public static JwtTokenUtils getJwtTokenBean() {
        return ac.getBean(JwtTokenUtils.class);
    }

    /**
     * 根据负责生成JWT的token
     */
    private static String generateToken(Map<String, Object> claims) {
        return JWT.create().withHeader(claims).withExpiresAt(generateExpirationDate())
                .sign(Algorithm.HMAC512(getJwtTokenBean().secret));
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static DecodedJWT getClaimsFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(getJwtTokenBean().secret)).build().verify(token);
    }

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + getJwtTokenBean().expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public static String getUserNameFromToken(String token) {
        String username;
        try {
            DecodedJWT decodedJwt = getClaimsFromToken(token);
            username = decodedJwt.getHeaderClaim(CLAIM_KEY_USERNAME).asString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public static boolean validateToken(String token, UserDetails userDetails) {
        return Objects.equals(getUserNameFromToken(token),userDetails.getUsername()) && isTokenExpired(token);
    }

    /**
     * 校验token
     *
     * @param token 客户端传入的token
     * @return token是否有效
     */
    public static boolean validate(String token) {
        try {
            getClaimsFromToken(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean isTokenExpired(String token) {
        return !getExpiredDateFromToken(token).before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiresAt();
    }

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public static boolean canRefresh(String token) {
        return isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public static String refreshToken(String token) {
        DecodedJWT jwt = getClaimsFromToken(token);
        Map<String, Object> claims = new HashMap<>(16);
        claims.putAll(jwt.getClaims());
        claims.remove(CLAIM_KEY_CREATED);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
