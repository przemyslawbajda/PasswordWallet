package com.wallet.demo.repository;

import com.wallet.demo.entity.SharedPassword;
import com.wallet.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SharedPasswordRepository extends JpaRepository<SharedPassword, Long> {

    public Set<SharedPassword> findAllByUser(User user);
}
