package com.example.learnmalldemo.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成工具
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-05-26 15:20
 */
@Slf4j
@Component
public class JwtTokenUtils {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return JWT.create().withHeader(claims).withExpiresAt(generateExpirationDate()).sign(Algorithm.HMAC512(secret));
    }

    /**
     * 从token中获取JWT中的负载
     */
    private DecodedJWT getClaimsFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            username = getClaimsFromToken(token).getSubject();
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
    public boolean validateToken(String token, UserDetails userDetails) {
        return getUserNameFromToken(token).equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        return !getExpiredDateFromToken(token).before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiresAt();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        DecodedJWT jwt = getClaimsFromToken(token);
        Map<String, Object> claims = new HashMap<>(16);
        jwt.getClaims().forEach(claims::put);
        claims.remove(CLAIM_KEY_CREATED);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
