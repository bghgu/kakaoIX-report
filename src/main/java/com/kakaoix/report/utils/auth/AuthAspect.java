package com.kakaoix.report.utils.auth;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.Token;
import com.kakaoix.report.service.JwtService;
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

/**
 * JWT 검증 AOP
 */
@Component
@Aspect
public class AuthAspect {

    private final static String AUTHORIZATION = "Authorization";

    /**
     * 실패 시 기본 반환 Response
     */
    private final static DefaultRes DEFAULT_RES = DefaultRes.builder().statusCode(StatusCode.UNAUTHORIZED).responseMessage(ResponseMessage.UNAUTHORIZED).build();
    private final static ResponseEntity<DefaultRes> RES_RESPONSE_ENTITY = new ResponseEntity<DefaultRes>(DEFAULT_RES, HttpStatus.UNAUTHORIZED);

    private final HttpServletRequest httpServletRequest;

    private final UserService userService;

    private final JwtService jwtService;

    /**
     * Repository 의존성 주입
     */
    public AuthAspect(final HttpServletRequest httpServletRequest, final UserService userService, final JwtService jwtService) {
        this.httpServletRequest = httpServletRequest;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Around("@annotation(com.kakaoix.report.utils.auth.Auth)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        final String jwt = httpServletRequest.getHeader(AUTHORIZATION);

        //토큰 존재 여부 확인
        if (jwt == null) {
            return RES_RESPONSE_ENTITY;
        }

        //토큰 해독
        final Token token = jwtService.decode(jwt);

        //토큰 검사
        if (token == null) {
            return RES_RESPONSE_ENTITY;
        }

        final Optional<User> user = userService.getUser(token.getUser_idx());

        //유효 사용자 검사
        if (!user.isPresent()) {
            return RES_RESPONSE_ENTITY;
        }

        return pjp.proceed(pjp.getArgs());
    }
}
