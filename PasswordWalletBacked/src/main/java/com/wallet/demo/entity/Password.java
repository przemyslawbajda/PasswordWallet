package com.wallet.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="password")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String webAddress;

    private String description;

    private String login;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    public Password(String password, String webAddress, String description, String login) {
        this.password = password;
        this.webAddress = webAddress;
        this.description = description;
        this.login = login;
    }

    public Password() {
    }
}
