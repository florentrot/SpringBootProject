package com.example.demo.controllers;

import com.example.demo.entities.UserAccount;
import com.example.demo.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SecurityController {

    @Autowired
    RegisterService registerService;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);

        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(UserAccount user){
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        registerService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }



}
