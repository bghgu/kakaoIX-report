package com.kakaoix.report.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ds on 2018-08-30.
 */

@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String password;
}
