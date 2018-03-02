package com.skyrimAuction.controllers;

import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.securityService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/api/registerUser")
    public ResponseEntity<User> registerAccount(@RequestBody User user) {
        User response = accountService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/api/checkLogin")
    public boolean login(){
        return true;
    }
}
