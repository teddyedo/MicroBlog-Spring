package com.example.microblog.jwt;

/**
 * @author Allari Edoardo
 * Some info to handle JWT
 */

public class JwtProperties {
    public static final String SECRET = "CodingAllWithThisSecret"; //Not so safety =)
    public static final int EXPIRATION_TIME = 864000000; // 10 Days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
