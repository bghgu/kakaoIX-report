package com.kakaoix.report.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by ds on 2018-08-29.
 */

/**
 * 기본 Response Class
 * ResponseEntity에서 무조건 HttpStatus 200으로 return하고 각 상황에 맞는 HttpStatus Code를 재 정의
 * @param <T> 반환할 결과 데이터 class
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultRes<T> {

    //재정의한 Response StatusCode
    private int statusCode;

    //Response Message
    private String responseMessage;

    //Response Data
    private T responseData;

    public DefaultRes(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.responseData = null;
    }
}
