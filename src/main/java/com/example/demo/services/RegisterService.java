package com.example.demo.services;

import com.example.demo.dao.UserAccountRepository;
import com.example.demo.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    UserAccountRepository userRepo;

    public UserAccount saveUser(UserAccount userAccount) {
       return userRepo.save(userAccount);
    }
}
