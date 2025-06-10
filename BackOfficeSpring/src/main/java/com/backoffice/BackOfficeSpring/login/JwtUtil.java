package com.backoffice.BackOfficeSpring.login;

import java.util.Date;
import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String SECRET_KEY = "tu_clave_secreta_que_debe_ser_mas_larga_para_hs256!";

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String username, String rol) {
        return Jwts.builder()
            .setSubject(username)
            .claim("rol", java.util.Collections.singletonList(rol))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 7 *86400000)) // 1 día
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
}
