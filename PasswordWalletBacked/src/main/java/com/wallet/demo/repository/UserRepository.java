package com.wallet.demo.repository;

import com.wallet.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Boolean existsByLogin(String login);

    public User findUserByLogin(String login);
}
