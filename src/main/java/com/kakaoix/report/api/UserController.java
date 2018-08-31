package com.kakaoix.report.api;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.res.DefaultRes;
import com.kakaoix.report.model.req.UserDto;
import com.kakaoix.report.service.UserService;
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
@RequestMapping("users")
public class UserController {

    private static final DefaultRes<User> FAIL_DEFAULE_RES = new DefaultRes<User>(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final UserService<User> userService;

    @Autowired
    public UserController(final UserService<User> userService) {
        this.userService = userService;
    }

    @GetMapping("/{user_idx}")
    public ResponseEntity<DefaultRes<User>> getUsers(@PathVariable final int user_idx) {
        try {
            return new ResponseEntity<DefaultRes<User>>(userService.findOne(user_idx), HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<User>>(FAIL_DEFAULE_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<DefaultRes<User>> postUsers(@RequestBody final UserDto userDto) {
        try {
            return new ResponseEntity<DefaultRes<User>>(userService.save(userDto), HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DefaultRes<User>>(FAIL_DEFAULE_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
