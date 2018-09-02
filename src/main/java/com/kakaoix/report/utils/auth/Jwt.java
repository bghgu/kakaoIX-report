package com.kakaoix.report.utils.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import static com.auth0.jwt.JWT.require;

/**
 * Created by ds on 2018-09-01.
 */

@Slf4j
public class Jwt {

    private static final String ISSUER = "kakaoIX";

    private static final String SECRET = "vji2k@#49cn292mfo@!$mxiap@#mknvldkm3$";

    public static String create(final int user_idx) {
        try {
            JWTCreator.Builder b = JWT.create();
            b.withIssuer(ISSUER);
            b.withClaim("user_idx", user_idx);
            return b.sign(Algorithm.HMAC256(SECRET));
        } catch (JWTCreationException JwtCreationException) {
            log.error(JwtCreationException.getMessage());
        }

        return null;
    }

    public static Token decode(final String token) {
        try {
            final JWTVerifier jwtVerifier = require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return new Token(decodedJWT.getClaim("user_idx").asLong().intValue());
        } catch (JWTVerificationException JwtVerificationException) {
            log.error(JwtVerificationException.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public static class Token {
        private int user_idx;

        public Token() {
        }

        public Token(final int user_idx) {
            this.user_idx = user_idx;
        }

        public int getUser_idx() {
            return user_idx;
        }
    }
}
