package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-08-31.
 */

@Data
public class TokenDto {
    private String token;

    public TokenDto(final String token) {
        this.token = token;
    }
}
