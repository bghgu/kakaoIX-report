package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.TokenDto;
import com.kakaoix.report.repository.UserRepository;
import com.kakaoix.report.service.JwtService;
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

    private final JwtService jwtService;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository, final JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public DefaultRes<TokenDto> login(final String email, final String password) {
        final String encryptPw = SHA512EncryptUtils.encrypt(password);
        final Optional<User> user = userRepository.findByEmailAndPassword(email, encryptPw);
        if (user.isPresent()) {
            user.get().setPassword("");
            final TokenDto token = new TokenDto(jwtService.createToken(user.get(), "token"));
            return DefaultRes.<TokenDto>builder().statusCode(StatusCode.OK).responseMessage(ResponseMessage.READ).responseData(token).build();
        }
        return DefaultRes.<TokenDto>builder().statusCode(StatusCode.NOT_FOUND).responseMessage(ResponseMessage.NOT_FOUND).build();
    }

    @Override
    public DefaultRes logout() {
        return null;
    }
}
