package com.wallet.demo.entity;

import com.wallet.demo.entity.Password;
import com.wallet.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="shared_password")
public class SharedPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",
    referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="password_id",
    referencedColumnName = "id")
    private Password password;


}
