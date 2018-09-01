package com.kakaoix.report.service;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.TokenDto;

/**
 * Created by ds on 2018-08-30.
 */

public interface LoginService {
    DefaultRes<TokenDto> login(final String email, final String password);
    DefaultRes logout();
}
