package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.DefaultRes;
import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.model.UserRes;
import com.kakaoix.report.repository.UserRepository;
import com.kakaoix.report.service.UserService;
import com.kakaoix.report.utils.ResponseMessage;
import com.kakaoix.report.utils.SHA512EncryptUtils;
import com.kakaoix.report.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by ds on 2018-08-31.
 */

/**
 * 회원 서비스 구현체
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Repository 의존성 주입
     */
    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 정보 상세 조회
     * @param user_idx 회원 고유 IDX
     * @return
     */
    @Override
    public DefaultRes<UserRes> findOne(final int user_idx) {
        final Optional<User> user = getUser(user_idx);
        if (user.isPresent()) {
            UserRes userRes = UserRes.builder()
                    .userIdx(user.get().getUserIdx())
                    .email(user.get().getEmail())
                    .name(user.get().getName())
                    .build();
            return DefaultRes.<UserRes>builder()
                    .statusCode(StatusCode.OK)
                    .responseMessage(ResponseMessage.READ_USER)
                    .responseData(userRes)
                    .build();
        }
        return DefaultRes.<UserRes>builder()
                .statusCode(StatusCode.NOT_FOUND)
                .responseMessage(ResponseMessage.NOT_FOUND_USER)
                .build();
    }

    /**
     * 회원 가입
     * @param userDto 회원가입 폼
     * @return
     */
    @Override
    public DefaultRes save(final UserDto userDto) {
        final Optional<User> checkUser = userRepository.findByEmail(userDto.getEmail());
        if (checkUser.isPresent()) {
            return DefaultRes.<User>builder()
                    .statusCode(StatusCode.NO_CONTENT)
                    .responseMessage(ResponseMessage.ALREADY_USER)
                    .build();
        }
        //비밀번호 암호화
        final String encryptPw = SHA512EncryptUtils.encrypt(userDto.getPassword());
        final User saveUser = User.builder()
                .email(userDto.getEmail())
                .password(encryptPw)
                .name(userDto.getName())
                .build();
        userRepository.save(saveUser);
        return DefaultRes.<User>builder()
                .statusCode(StatusCode.CREATED)
                .responseMessage(ResponseMessage.CREATED_USER)
                .build();
    }

    /**
     * 회원 조회
     * @param user_idx 회원 고유 IDX
     * @return 회원
     */
    @Override
    public Optional<User> getUser(final int user_idx) {
        return userRepository.findById(user_idx);
    }
}
