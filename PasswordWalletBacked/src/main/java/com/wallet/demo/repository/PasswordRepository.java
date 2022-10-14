package com.wallet.demo.repository;

import com.wallet.demo.entity.Password;
import com.wallet.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    public Set<Password> findAllByUser(User user);
    public Password findPasswordById(Long passwordId);


}
