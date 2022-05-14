package com.example.rentit.userservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 3/10/2022
 */
public class SecurityUtil {

    //private static final String secretKey = "secret";
    private static final byte[] secretKey = new byte[10];
    private static final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    public static final String AUTHORIZATION_PREFIX = "Bearer ";
    public static final int ACCESS_TOKEN_EXP_MILL = 1 * 10 * 1000; // 15 minutes
    public static final int REFRESH_TOKEN_EXP_MILL = 40 * 60 * 1000; // 40 minutes



    @PostConstruct
    private void init(){
        Random r = new Random();
        r.nextBytes(secretKey);
    }

    public static Algorithm getAlgorithm() {
        return algorithm;
    }
    public static byte[] getSecretKey(){
        return secretKey;
    }

    public static String verifyAndGetSubject(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return username;
    }
    public static DecodedJWT getDecodedJWT(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public static void handleTokenException(Exception exception, HttpServletResponse response) throws IOException {
        response.setHeader("error", exception.getMessage());
        response.setStatus(FORBIDDEN.value());
        //response.sendError(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

    public static boolean isAuthorizationHeaderValid(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader != null && authorizationHeader.startsWith(AUTHORIZATION_PREFIX);
    }

    public static String getTokenFromAuthHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring(AUTHORIZATION_PREFIX.length());
        return token;
    }
}
