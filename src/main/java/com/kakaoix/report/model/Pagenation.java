package com.kakaoix.report.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ds on 2018-09-02.
 */

@Data
@Builder
public class Pagenation {
    private int offset;
    private int limit;
    private String sort;
}
