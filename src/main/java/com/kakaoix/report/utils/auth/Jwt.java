package com.kakaoix.report.utils.auth;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by ds on 2018-09-01.
 */

public class Jwt {

    private static final Logger LOG = Logger.getLogger(Jwt.class.getSimpleName());

    private static final String ISSUER = "kakaoIX";

    private static final String SECRET = "vji2k@#49cn292mfo@!$mxiap@#mknvldkm3$";

    public static String create(final int user_idx) {
        try {
            JWTCreator.Builder b = com.auth0.jwt.JWT.create();
            b.withIssuer(ISSUER);
            b.withClaim("user_idx", user_idx);
            return b.sign(Algorithm.HMAC256(SECRET));
        } catch (JWTCreationException jce) {
            LOG.error(jce.getMessage());
        }

        return null;
    }

    public static Token decode(final String token) {
        try {
            JWTVerifier v = com.auth0.jwt.JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
            DecodedJWT djwt = v.verify(token);
            return new Token(djwt.getClaim("user_idx").asLong().intValue());
        } catch (JWTVerificationException jve) {
            LOG.error(jve.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
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
