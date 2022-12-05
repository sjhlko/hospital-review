package com.hospital.hospitalreview.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {

    public static Claims extractClaims(String  token, String key){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public static boolean isExpired(String token, String secretKey){
        Date expiredDate = extractClaims(token, secretKey).getExpiration();
        return expiredDate.before(new Date());
    }
    public static String createToken(String userName, String key, long expireTimeMs) {
        Claims claims = Jwts.claims(); // 일종의 map
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact()
                ;
    }
}
