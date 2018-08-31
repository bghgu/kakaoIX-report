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

    @Override
    public DefaultRes<User> findOne(final int user_idx) {
        final Optional<User> user = userRepository.findById(user_idx);
        if (user.isPresent()) {
            return DefaultRes.<User>builder().statusCode(StatusCode.OK).responseMessage(ResponseMessage.READ).responseData(user.get()).build();
        }
        return DefaultRes.<User>builder().statusCode(StatusCode.NOT_FOUND).responseMessage(ResponseMessage.NOT_FOUND).build();
    }

    @Override
    public DefaultRes<User> save(final UserDto userDto) {
        final String encryptPw = SHA512EncryptUtils.encrypt(userDto.getPassword());
        final User user = User.builder().email(userDto.getEmail()).password(encryptPw).name(userDto.getName()).build();
        userRepository.save(user);
        return DefaultRes.<User>builder().statusCode(StatusCode.CREATED).responseMessage(ResponseMessage.CREATED).build();
    }
}
