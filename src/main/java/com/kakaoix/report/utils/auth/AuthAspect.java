package com.kakaoix.report.utils.auth;

import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kakaoix.report.utils.auth.Jwt;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ds on 2018-09-01.
 */

@Component
@Aspect
public class AuthAspect {

    private final static String AUTHORIZATION = "Authorization";

    private final static DefaultRes DEFAULT_RES = DefaultRes.builder().statusCode(StatusCode.NOT_FOUND).responseMessage(ResponseMessage.NOT_FOUND).build();

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public AuthAspect(final HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Around("@annotation(com.kakaoix.report.utils.auth.Auth)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        final String jwt = httpServletRequest.getHeader(AUTHORIZATION);

        if (jwt == null) {
            return new ResponseEntity<DefaultRes>(DEFAULT_RES, HttpStatus.SERVICE_UNAVAILABLE);
        }

        Token token = Jwt.decode(jwt);

        if (token == null) {
            return new ResponseEntity<DefaultRes>(DEFAULT_RES, HttpStatus.SERVICE_UNAVAILABLE);
        }

//        User user = us.getUser(t.getUidx());
//
//        if (user == null) {
//            return new ResponseEntity<DefaultRes>(DEFAULT_RES, HttpStatus.UNAUTHORIZED);
//        }

        Object[] params = pjp.getArgs();
//
//        for (int i = 0; i < params.length; i++) {
//            if (params[i] instanceof User) {
//                params[i] = u;
//                break;
//            }
//        }

        return pjp.proceed(params);
    }
}
