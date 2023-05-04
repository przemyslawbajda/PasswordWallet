package com.wallet.demo.repository;

import com.wallet.demo.entity.IpCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpCheckRepository extends JpaRepository<IpCheck, Long> {

    public boolean existsByIp(String ip);

    public IpCheck findByIp(String ip);

}
