package com.wallet.demo.repository;

import com.wallet.demo.entity.LoginAttempt;
import com.wallet.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    public LoginAttempt findFirstByUserAndIsSuccessfulOrderByAttemptDateDesc(User user, boolean isSuccessful);
}
