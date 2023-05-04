package com.wallet.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name="ip_check")
public class IpCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private boolean isBlocked;

    private LocalDateTime lockTime;

    private int numberOfIncorrectLoginAttempts;
}
