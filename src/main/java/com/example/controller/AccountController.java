package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.RegisterException;
import com.example.service.AccountService;

@Controller
public class AccountController {
    
    private AccountService as;

    @Autowired
    public AccountController(AccountService as) {
        this.as = as;
    }

    @GetMapping("user")
    public @ResponseBody List<Account> getAllAccounts() {
        return as.getAllAccounts();
    }

    @PostMapping("register")
    public @ResponseBody ResponseEntity<Account> createAccount(@RequestBody Account acc) {
        Account persistedAcc = as.register(acc);
        return ResponseEntity.status(200).body(persistedAcc);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ResponseEntity<String> handleDuplicateUsername(DuplicateUsernameException ex) {
        ResponseEntity<String> re = ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        System.out.println(re.getStatusCode());
        return re;
    }

    @ExceptionHandler(RegisterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<String> handleRegisterException(RegisterException ex) {
        ResponseEntity<String> re = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        System.out.println(re.getStatusCode());
        return re;
    }
}
