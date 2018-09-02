package com.kakaoix.report.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by ds on 2018-08-29.
 */

@Data
@Entity
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    private String name;

    private String email;

    private String password;
}
