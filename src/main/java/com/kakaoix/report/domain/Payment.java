package com.kakaoix.report.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ds on 2018-08-30.
 */

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_idx;

    private int user_idx;

    private int item_idx;

    private int quantity;

    private LocalDateTime payment_at;
}
