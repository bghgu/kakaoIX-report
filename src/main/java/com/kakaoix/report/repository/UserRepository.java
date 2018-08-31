package com.kakaoix.report.repository;

import com.kakaoix.report.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ds on 2018-08-29.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
