package com.wallet.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String passwordHash;

    @JsonIgnore
    private String salt;

    private Boolean isPasswordKeptAsHash;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Password> passwords;

    public User(String login, boolean isPasswordKeptAsHash){
        this.login = login;
        this.isPasswordKeptAsHash = isPasswordKeptAsHash;
    }

    public User() {
    }
}
