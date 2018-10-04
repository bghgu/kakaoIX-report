package com.kakaoix.report.utils.auth;

import java.lang.annotation.*;

/**
 * Created by ds on 2018-09-01.
 */

/**
 * JWT 검증 AOP
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Auth {

}