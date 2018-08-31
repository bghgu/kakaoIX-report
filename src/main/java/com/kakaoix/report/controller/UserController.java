package com.kakaoix.report.controller;

import com.kakaoix.report.model.res.DefaultRes;
import com.kakaoix.report.model.req.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ds on 2018-08-29.
 */

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("/{user_idx}")
    public ResponseEntity<DefaultRes> getUsers(@PathVariable final int user_idx) {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<DefaultRes> postUsers(@RequestBody final UserDto userDto) {
        return null;
    }
}
