package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
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
        return ar.save(acc);
    }
}
