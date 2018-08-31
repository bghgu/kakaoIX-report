package com.kakaoix.report.model.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by ds on 2018-08-29.
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
