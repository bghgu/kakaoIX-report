package com.kakaoix.report.utils.auth;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.apache.log4j.Logger;

/**
 * Created by ds on 2018-09-01.
 */

@Data
public class Jwt {

    private static final Logger LOG = Logger.getLogger(Jwt.class.getSimpleName());

    private static final String ISSUER = "hitit";
    private static final String SECRET = "tmvmfld!";

    /**
     * Methods to create json web token string object.
     *
     * @param uidx
     * @return
     */
    public static String create(final long uidx) {
        LOG.debug("create");
        try {
            JWTCreator.Builder b = com.auth0.jwt.JWT.create();
            b.withIssuer(ISSUER);
            b.withClaim("uidx", uidx);

            return b.sign(Algorithm.HMAC256(SECRET));
        } catch (JWTCreationException jce) {
            LOG.error(jce.getMessage());
        }

        return null;
    }

    /**
     * Methods to decode json web token and return token object.
     *
     * @param token
     * @return
     */
    public static Token decode(final String token) {
        LOG.debug("decode");

        try {
            JWTVerifier v = com.auth0.jwt.JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
            DecodedJWT djwt = v.verify(token);
            return new Token(djwt.getClaim("uidx").asLong().longValue());
        } catch (JWTVerificationException jve) {
            LOG.error(jve.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return null;
    }

    public static class Token {
        private static final Logger LOG = Logger.getLogger(Token.class.getSimpleName());

        private long uidx;

        public Token() {
        }

        public Token(final long uidx) {
            LOG.debug("Token");

            this.uidx = uidx;
        }

        public long getUidx() {
            LOG.debug("getUidx");
            return uidx;
        }
    }
}
