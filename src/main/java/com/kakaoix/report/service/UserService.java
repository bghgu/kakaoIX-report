package com.kakaoix.report.service;

import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.model.DefaultRes;

/**
 * Created by ds on 2018-08-30.
 */

public interface UserService<User> {
    DefaultRes<User> findOne(final int user_idx);

    DefaultRes<User> save(final UserDto userDto);
}
