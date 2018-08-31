package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.res.DefaultRes;
import com.kakaoix.report.repository.UserRepository;
import com.kakaoix.report.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ds on 2018-08-30.
 */

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DefaultRes<User> login(String id, String password) {
        return null;
    }

    @Override
    public DefaultRes<User> logout() {
        return null;
    }
}
