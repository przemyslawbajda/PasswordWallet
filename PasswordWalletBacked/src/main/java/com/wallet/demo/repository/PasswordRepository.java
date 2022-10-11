package com.wallet.demo.repository;

import com.wallet.demo.entity.Password;
import com.wallet.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    public List<Password> findPasswordsByLogin(String login);

    public List<Password> findPasswordByLogin(String login);

    public List<Password> getPasswordsByLogin(String login);
    public List<Password> findAllByUser(User user);
}
