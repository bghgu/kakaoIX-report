package com.kakaoix.report.api;

import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.TokenDto;
import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.service.LoginService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ds on 2018-08-29.
 */

@RestController
@RequestMapping("")
public class LoginController {

    private static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final LoginService loginService;

    @Autowired
    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("login")
    public ResponseEntity<DefaultRes<TokenDto>> login(@RequestBody final UserDto userDto) {
        try {
            return new ResponseEntity<DefaultRes<TokenDto>>(loginService.login(userDto.getEmail(), userDto.getPassword()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<TokenDto>>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("logout")
    public ResponseEntity<DefaultRes> logout() {
        try {
            return new ResponseEntity<DefaultRes>(loginService.logout(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
