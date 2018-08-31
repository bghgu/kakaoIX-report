package com.kakaoix.report.service;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.res.DefaultRes;

/**
 * Created by ds on 2018-08-30.
 */

public interface LoginService {
    DefaultRes<User> login(final String email, final String password);
    DefaultRes<User> logout();
}
