package com.kakaoix.report.service;

import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.TokenRes;
import com.kakaoix.report.model.UserDto;

/**
 * Created by ds on 2018-08-30.
 */

/**
 * 로그인 서비스 인터페이스
 */
public interface LoginService {
    DefaultRes<TokenRes> login(final UserDto userDto);
}
