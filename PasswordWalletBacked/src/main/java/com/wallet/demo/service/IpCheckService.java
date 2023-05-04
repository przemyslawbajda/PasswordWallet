package com.wallet.demo.service;

import com.wallet.demo.entity.IpCheck;
import com.wallet.demo.repository.IpCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IpCheckService {

    @Autowired
    IpCheckRepository ipCheckRepository;

    public void addIncorrectAttempt(String requestIpAddress) {

        if(!ipCheckRepository.existsByIp(requestIpAddress)){
            IpCheck newIpCheck = new IpCheck();
            newIpCheck.setIp(requestIpAddress);
            newIpCheck.setBlocked(false);
            newIpCheck.setNumberOfIncorrectLoginAttempts(1);

            ipCheckRepository.save(newIpCheck);
            return;
        }

        IpCheck ipCheck = ipCheckRepository.findByIp(requestIpAddress);
        int numberOfIncorrectAttempts = ipCheck.getNumberOfIncorrectLoginAttempts();

        switch (numberOfIncorrectAttempts){
            case 1:
                ipCheck.setNumberOfIncorrectLoginAttempts(numberOfIncorrectAttempts+1);
                ipCheck.setLockTime(LocalDateTime.now().plusSeconds(10));
                break;
            case 2:
                ipCheck.setNumberOfIncorrectLoginAttempts(numberOfIncorrectAttempts+1);
                ipCheck.setLockTime(LocalDateTime.now().plusSeconds(10));
                break;
            case 3:
                ipCheck.setNumberOfIncorrectLoginAttempts(numberOfIncorrectAttempts+1);
                ipCheck.setBlocked(true);
                break;
        }

        ipCheckRepository.save(ipCheck);
    }

    public void resetIncorrectAttemptsNumber(String requestIpAddress){

        if(ipCheckRepository.existsByIp(requestIpAddress)){
            IpCheck ipCheck = ipCheckRepository.findByIp(requestIpAddress);
            ipCheckRepository.delete(ipCheck);
        }
    }

    public boolean checkBlockade(String requestIpAddress) {

        if(ipCheckRepository.existsByIp(requestIpAddress)){
            IpCheck ipCheck = ipCheckRepository.findByIp(requestIpAddress);
            return ipCheck.isBlocked();
        }
        return false;
    }

    public boolean checkLockTime(String requestIpAddress) {

        if(!ipCheckRepository.existsByIp(requestIpAddress)){
            return false;
        }

        IpCheck ipCheck = ipCheckRepository.findByIp(requestIpAddress);


        if(ipCheck.getLockTime() != null && ipCheck.getLockTime().isAfter(LocalDateTime.now())){
            return true;
        }

        return false;
    }

}
