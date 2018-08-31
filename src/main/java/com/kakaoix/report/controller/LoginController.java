package com.kakaoix.report.controller;

import com.kakaoix.report.model.res.DefaultRes;
import com.kakaoix.report.model.req.UserDto;
import com.kakaoix.report.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ds on 2018-08-29.
 */

@RestController
@RequestMapping("")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("login")
    public ResponseEntity<DefaultRes> login(@RequestBody final UserDto userDto) {
        return null;
    }

    @GetMapping("logout")
    public ResponseEntity<DefaultRes> logout() {
        return null;
    }
}
