package com.example.rentit.userservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 3/10/2022
 */
public class HashAlgoUtil {

    //private static final String secretKey = "secret";
    private static final byte[] secretKey = new byte[10];

    @PostConstruct
    private void init(){
        Random r = new Random();
        r.nextBytes(secretKey);
    }

    public static byte[] getSecretKey(){
        return secretKey;
    }

    public static String verifyToken(String token) {
        String refresh_token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(HashAlgoUtil.getSecretKey());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refresh_token);
        String username = decodedJWT.getSubject();
    }
}
