package com.wallet.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String passwordHash;

    private String salt;

    private boolean isPasswordKeptAsHash;

    @OneToMany(mappedBy = "user")
    private Set<Password> passwords;

    public User(String login, boolean isPasswordKeptAsHash){
        this.login = login;
        this.isPasswordKeptAsHash = isPasswordKeptAsHash;
    }
}
