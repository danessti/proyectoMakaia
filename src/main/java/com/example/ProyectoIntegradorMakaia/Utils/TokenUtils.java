package com.example.ProyectoIntegradorMakaia.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "b289e50391fa8791cf6eee9b6cfbcaf868ba7b8bebedfa7840b08dd436a97e309ee46d01f6fa34c0bf366e9016c6316c818940e8f9d9bb690717f2f47e3c22449b02ff40c35ec9a9a4c84062644abeaba69757c4308244da9076500a229deff795b5492fe2fc593b5b1ad31cda9be03332b8e9cdc406e6976c9a65ebf30d5d2fc61ab7cadc45052000e3e7a2d4f7618769a715fd1a3599091a48d13f990d38366500bc8c250a5956a619511d8a99056903331a68c0db0106bdf9ebc18353cc4d45c9d66b76098e97e54de6e9ff265f92797e38ee11ecc71b27cfcfcfcc03092956c3e38b5b08bfb5d3715788cfa9e4d9a6078db6fb4ce3adf55573e00a67d48d";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS =2_592_000L;

    public static String createToken (String username, String email){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);

        Map<String,Object> extra = new HashMap<>();
        extra.put("nombre", username);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key getKey(){
        byte[] keyBytes= Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public  static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims=Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }catch (JwtException e) {
            return null;
        }
    }

}