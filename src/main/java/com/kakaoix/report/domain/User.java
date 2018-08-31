package com.kakaoix.report.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ds on 2018-08-29.
 */

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_idx;

    private String name;

    private String id;

    private String password;
}
