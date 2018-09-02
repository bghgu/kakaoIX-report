package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.TokenRes;
import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.repository.UserRepository;
import com.kakaoix.report.service.LoginService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.SHA512EncryptUtils;
import com.kakaoix.report.utils.StatusCode;
import com.kakaoix.report.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ds on 2018-08-30.
 */

/**
 * 로그인 서비스 구현체
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    /**
     * Repository 의존성 주입
     */
    @Autowired
    public LoginServiceImpl(final UserRepository userRepository, final JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    /**
     * 로그인
     * 아이디와 비밀번호 동시 비교
     * @param userDto 로그인 폼
     * @return JWT 토큰값
     */
    @Override
    public DefaultRes<TokenRes> login(final UserDto userDto) {
        final String encryptPw = SHA512EncryptUtils.encrypt(userDto.getPassword());
        final Optional<User> user = userRepository.findByEmailAndPassword(userDto.getEmail(), encryptPw);
        if (user.isPresent()) {
            user.get().setPassword("");
            final TokenRes tokenDto = new TokenRes(jwtService.create(user.get().getUserIdx()));
            return DefaultRes.<TokenRes>builder()
                    .statusCode(StatusCode.OK)
                    .responseMessage(ResponseMessage.LOGIN_SUCCESS)
                    .responseData(tokenDto).build();
        }
        return DefaultRes.<TokenRes>builder()
                .statusCode(StatusCode.BAD_REQUEST)
                .responseMessage(ResponseMessage.LOGIN_FAIL).build();
    }
}
