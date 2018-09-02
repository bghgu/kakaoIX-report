package com.kakaoix.report.repository;

import com.kakaoix.report.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by ds on 2018-08-29.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * 로그인 시 email과 password를 비교후 결과값 반환
     * @param email 이메일
     * @param password 비밀번호
     * @return user
     */
    Optional<User> findByEmailAndPassword(final String email, final String password);

    Optional<User> findByEmail(final String email);
}
