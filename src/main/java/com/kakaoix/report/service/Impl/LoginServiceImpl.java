package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.res.DefaultRes;
import com.kakaoix.report.repository.UserRepository;
import com.kakaoix.report.service.LoginService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.SHA512EncryptUtils;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public DefaultRes<User> login(final String email, final String password) {
        final String encryptPw = SHA512EncryptUtils.encrypt(password);

        final Optional<User> user = userRepository.findByEmailAndPassword(email, encryptPw);
        if (user.isPresent()) {

            return DefaultRes.<User>builder().statusCode(StatusCode.OK).responseMessage(ResponseMessage.READ).responseData(user.get()).build();
        }
        return DefaultRes.<User>builder().statusCode(StatusCode.NOT_FOUND).responseMessage(ResponseMessage.NOT_FOUND).build();
    }

    @Override
    public DefaultRes<User> logout() {
        return null;
    }
}
