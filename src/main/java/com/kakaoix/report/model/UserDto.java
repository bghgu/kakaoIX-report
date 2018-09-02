package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-08-30.
 */

/**
 * 회원 가입 및 로그인을 위한 User Dto
 */
@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
}
