package com.example.springboot_blog.common.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUntil {
    private static final String secret = "hTwpNhZ0qFVXz4fwG93i0SXTm2TtFm+afXzasChE87g=";

    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

    //24小时过期
    private static final long expiration = 24*60*60 * 1000;
    //1. 生成token

    public  static String genToken( Map<String, Object> map){
        String compact = Jwts.builder()
                .setClaims(map)
                .signWith(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .compact();
        return compact;
    }
   //验证token
    public static Claims parseToken(String token){

        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        log.info("********"+token);
        Claims body = null;
        try {
            body = build.parseClaimsJws(token).getBody();

            return body;
        }catch (Exception e){
            e.printStackTrace();
            log.error("Token不合法");
        }
        return null;
    }
}
