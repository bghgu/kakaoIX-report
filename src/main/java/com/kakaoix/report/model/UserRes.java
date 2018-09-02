package com.kakaoix.report.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ds on 2018-09-02.
 */

/**
 * 회원 정보 조회 후 반환을 위한 Response Data
 */
@Data
@Builder
public class UserRes {
    private int userIdx;
    private String email;
    private String name;
}
