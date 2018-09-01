package com.kakaoix.report.service.Impl;

import com.kakaoix.report.domain.User;
import com.kakaoix.report.model.UserDto;
import com.kakaoix.report.model.DefaultRes;
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

@Service
public class UserServiceImpl implements UserService<User> {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 정보 상세 조회
     *
     * @param user_idx
     * @return
     */
    @Override
    public DefaultRes<User> findOne(final int user_idx) {
        final Optional<User> user = userRepository.findById(user_idx);
        if (user.isPresent()) {
            return DefaultRes.<User>builder()
                    .statusCode(StatusCode.OK)
                    .responseMessage(ResponseMessage.READ)
                    .responseData(user.get())
                    .build();
        }
        return DefaultRes.<User>builder()
                .statusCode(StatusCode.NOT_FOUND)
                .responseMessage(ResponseMessage.NOT_FOUND)
                .build();
    }

    /**
     * 회원 가입
     *
     * @param userDto
     * @return
     */
    @Override
    public DefaultRes<User> save(final UserDto userDto) {
        final Optional<User> checkUser = userRepository.findByEmail(userDto.getEmail());
        if (checkUser.isPresent()) {
            return DefaultRes.<User>builder()
                    .statusCode(StatusCode.NO_CONTENT)
                    .responseMessage(ResponseMessage.ALREADY)
                    .build();
        }
        final String encryptPw = SHA512EncryptUtils.encrypt(userDto.getPassword());
        final User saveUser = User.builder()
                .email(userDto.getEmail())
                .password(encryptPw)
                .name(userDto.getName())
                .build();
        userRepository.save(saveUser);
        return DefaultRes.<User>builder()
                .statusCode(StatusCode.CREATED)
                .responseMessage(ResponseMessage.CREATED)
                .build();
    }
}
