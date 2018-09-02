package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-08-31.
 */

/**
 * 로그인 성공 후 Token Return을 위한 Dto
 */
@Data
public class TokenRes {
    private String token;

    public TokenRes(final String token) {
        this.token = token;
    }
}
