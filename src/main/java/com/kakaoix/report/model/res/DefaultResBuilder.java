package com.kakaoix.report.model.res;

import lombok.Data;
import lombok.Setter;

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

	public DefaultRes<T> build() {
		return new DefaultRes<T>(this.statusCode, this.responseMessage, this.responseData);
	}
	
}

