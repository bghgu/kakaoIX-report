package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-09-02.
 */

/**
 * jwt 토큰에 담길 객체
 */
public class Token {
    private int user_idx;

    public Token() {
    }

    public Token(final int user_idx) {
        this.user_idx = user_idx;
    }

    public int getUser_idx() {
        return user_idx;
    }
}
