package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.TokenDto;
import com.kakaoix.report.repository.UserRepository;
import com.kakaoix.report.service.LoginService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.SHA512EncryptUtils;
import com.kakaoix.report.utils.StatusCode;
import com.kakaoix.report.utils.auth.Jwt;
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

    /**
     * 로그인
     * @param email
     * @param password
     * @return
     */
    @Override
    public DefaultRes<TokenDto> login(final String email, final String password) {
        final String encryptPw = SHA512EncryptUtils.encrypt(password);
        final Optional<User> user = userRepository.findByEmailAndPassword(email, encryptPw);
        if (user.isPresent()) {
            user.get().setPassword("");
            final TokenDto tokenDto = new TokenDto(Jwt.create(user.get().getUserIdx()));
            return DefaultRes.<TokenDto>builder()
                    .statusCode(StatusCode.OK)
                    .responseMessage(ResponseMessage.LOGIN_SUCCESS)
                    .responseData(tokenDto).build();
        }
        return DefaultRes.<TokenDto>builder()
                .statusCode(StatusCode.BAD_REQUEST)
                .responseMessage(ResponseMessage.LOGIN_FAIL).build();
    }

    /**
     * 로그아웃
     * @return
     */
    @Override
    public DefaultRes logout() {
        return null;
    }
}
