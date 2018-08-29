package com.kakaoix.report.model;

import lombok.Data;

/**
 * Created by ds on 2018-08-29.
 */

@Data
public class DefaultResBuilder<T> {
	
	// 재정의한 Response StatusCode
	private Integer statusCode;

	// Response Message
	private String responseMessage;

	// Response Data
	private T responseData;
	
	public DefaultResBuilder<T> setStatusCode(final Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	
	public DefaultResBuilder<T> setResponseMessage(final String responseMessage) {
		this.responseMessage = responseMessage;
		return this;
	}
	
	public DefaultResBuilder<T> setResponseResult(final T responseData) {
		this.responseData = responseData;
		return this;
	}
	
	public DefaultRes<T> build() {
		return new DefaultRes<T>(this.statusCode, this.responseMessage, this.responseData);
	}
	
}

