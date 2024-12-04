package com.example.springboot_blog;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import javax.security.auth.kerberos.EncryptionKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTest {
    private static final String secret = "hTwpNhZ0qFVXz4fwG93i0SXTm2TtFm+afXzasChE87g=";  //这个编码能欧借助secret通过编码生成  Key

    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

    //24小时过期
    private static final long expiration = 24*60*60 * 1000;
    //1. 生成token
    @Test
    public void genToken(){

        Map<String, Object> map = new HashMap<>();
        map.put("id", 156666);
        map.put("name", "zhangsan");
        String compact = Jwts.builder()
                .setClaims(map)
                .signWith(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .compact();
        System.out.println(compact);
    }

    //2. 校验token
    @Test
    public void parseToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbmdzYW4iLCJpZCI6MTU2NjY2LCJpYXQiOjE3MzMwMTM4NzYsImV4cCI6MTczMzEwMDI3Nn0.VS90EWls-9tTvbcyXS4X8KzGHrgNBFU0YkM2kV4dErc";
        //jwt解析器

        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();

        try {
            Object body = build.parse(token).getBody();
            System.out.println(body);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("token 非法");
        }

    }

    //3. 生成key  能达到secret 获取key
    @Test
    public void genKey(){

        SecretKey secretKey  = Keys.secretKeyFor(SignatureAlgorithm.HS256);  //拿到一个secret 但还需要将其转化为字符串
        String encode = Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(encode);
    }
}
