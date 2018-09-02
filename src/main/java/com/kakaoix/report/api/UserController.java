package com.kakaoix.report.api;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.model.UserRes;
import com.kakaoix.report.service.UserService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.StatusCode;
import com.kakaoix.report.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ds on 2018-08-29.
 */

@RestController
@RequestMapping("users")
public class UserController {

    /**
     * 실패 시 기본 반환 Response
     */
    private static final DefaultRes<User> FAIL_DEFAULT_RES = new DefaultRes<User>(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);

    private final UserService userService;

    /**
     * 서비스 의존성 주입
     */
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * 현재 로그인한 사용자 자신의 마이 페이지 조회
     *
     * @param user_idx 회원 고유 IDX
     * @return 회원 정보
     */
    @GetMapping("/{user_idx}")
    @Auth
    public ResponseEntity<DefaultRes<UserRes>> getUsers(@PathVariable final int user_idx) {
        try {
            return new ResponseEntity<>(userService.findOne(user_idx), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>((MultiValueMap<String, String>) FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 회원 가입
     *
     * @param userDto 회원가입 폼
     * @return 가입 결과 메시지
     */
    @PostMapping("")
    public ResponseEntity<DefaultRes> postUsers(@RequestBody final UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
