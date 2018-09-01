package com.kakaoix.report.service;

import com.kakaoix.report.utils.SHA512EncryptUtils;
import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ds on 2018-08-31.
 */

@Service
public class JwtService {

    @Value("{JWT.SALT")
    private String SALT;

    public <T> String createToken(final T data, final String key) {
        final String jwt = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setId(key)
                .setExpiration(getTime())
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS512, SHA512EncryptUtils.encrypt(SALT))
                .compact();
        return jwt;
    }

    public Map<String, Object> getToken(final String key) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SHA512EncryptUtils.encrypt(SALT))
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            //throw new UnauthorizedException();
        }
        @SuppressWarnings("unchecked")
        final Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
        return value;
    }

    public String getAuthId(final String key) {
        return this.getToken(key).get("id").toString();
    }

    public boolean isValuedToken(final String jwt) {
        try {
            final Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SHA512EncryptUtils.encrypt(SALT))
                    .parseClaimsJws(jwt);
            return true;
        }catch (Exception e) {
            return false;
            //throw new UnauthorizedException();
        }
    }

    private Date getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 24);
        return cal.getTime();
    }
}
