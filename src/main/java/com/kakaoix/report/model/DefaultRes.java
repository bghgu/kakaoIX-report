package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-08-29.
 */

@Data
public class DefaultRes<T> {

    //재정의한 Response StatusCode
    private int statusCode;

    //Response Message
    private String responseMessage;

    //Response Data
    private T responseData;

    public DefaultRes() {}

    public DefaultRes(final int statusCode, final String responseMessage, final T responseData) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public DefaultRes(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.responseData = null;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public T getResponseData() {
        return responseData;
    }
}
