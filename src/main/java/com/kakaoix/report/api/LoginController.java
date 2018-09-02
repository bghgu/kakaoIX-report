package com.kakaoix.report.api;

import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.model.TokenRes;
import com.kakaoix.report.service.JwtService;
import com.kakaoix.report.service.LoginService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ds on 2018-08-29.
 */

@RestController
@RequestMapping("")
public class LoginController {

    /**
     * 실패 시 기본 반환 Response
     */
    private static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final LoginService loginService;

    /**
     * 서비스 의존성 주입
     */
    @Autowired
    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Login
     * @param userDto 로그인 폼
     * @return JWT Token 값
     */
    @PostMapping("login")
    public ResponseEntity<DefaultRes<TokenRes>> login(@RequestBody final UserDto userDto) {
        try {
            return new ResponseEntity<>(loginService.login(userDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<TokenRes>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
