package com.kakaoix.report.service;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.model.UserRes;

import java.util.Optional;

/**
 * Created by ds on 2018-08-30.
 */

/**
 * 유저 서비스 인터페이스
 */
public interface UserService {
    DefaultRes<UserRes> findOne(final int user_idx);

    DefaultRes save(final UserDto userDto);

    Optional<User> getUser(final int user_idx);
}
