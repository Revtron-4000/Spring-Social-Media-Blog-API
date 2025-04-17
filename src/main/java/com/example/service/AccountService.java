package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.RegisterException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository ar;

    @Autowired
    public AccountService(AccountRepository ar) {
        this.ar = ar;
    }

    public List<Account> getAllAccounts() {
        return ar.findAll();
    }

    public Account register(Account acc) {
        if (ar.findByUsername(acc.getUsername()) != null) {
            // System.out.println("This username already exists.");
            throw new DuplicateUsernameException("The username '" + acc.getUsername() + "' already exists.");
        }

        if (acc.getUsername().isBlank()) {
            throw new RegisterException("This username is blank.");
        }

        if (acc.getPassword().length() < 4) {
            throw new RegisterException("This password is less than 4 characters.");
        }

        return ar.save(acc);
    }

    public Account login(Account givenAcc) throws AuthenticationException {
        Account foundAcc = ar.findByUsernameAndPassword(givenAcc.getUsername(), givenAcc.getPassword())
            .orElseThrow(() -> new AuthenticationException("Credentials are invalid. Check your username and password."));

        return foundAcc;
    }
}
