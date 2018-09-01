package com.kakaoix.report.utils.auth;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.service.UserService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by ds on 2018-09-01.
 */

@Component
@Aspect
public class AuthAspect {

    private final static String AUTHORIZATION = "Authorization";

    private final static DefaultRes DEFAULT_RES = DefaultRes.builder().statusCode(StatusCode.UNAUTHORIZED).responseMessage(ResponseMessage.UNAUTHORIZED).build();
    private final static ResponseEntity<DefaultRes> RES_RESPONSE_ENTITY = new ResponseEntity<DefaultRes>(DEFAULT_RES, HttpStatus.UNAUTHORIZED);
    private final HttpServletRequest httpServletRequest;

    private final UserService userService;

    @Autowired
    public AuthAspect(final HttpServletRequest httpServletRequest, final UserService userService) {
        this.httpServletRequest = httpServletRequest;
        this.userService = userService;
    }

    @Around("@annotation(com.kakaoix.report.utils.auth.Auth)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        final String jwt = httpServletRequest.getHeader(AUTHORIZATION);

        if (jwt == null) {
            return RES_RESPONSE_ENTITY;
        }

        final Jwt.Token token = Jwt.decode(jwt);

        if (token == null) {
            return RES_RESPONSE_ENTITY;
        }

        Optional<User> user = userService.getUser(token.getUser_idx());

        if (!user.isPresent()) {
            return RES_RESPONSE_ENTITY;
        }

        Object[] params = pjp.getArgs();
        Object[] rerunParams = new Object[params.length+1];

        for (int i = 0; i < params.length; i++) {
            rerunParams[i] = params[i];
        }
        rerunParams[params.length] = user.get();

        return pjp.proceed(params);
    }
}
