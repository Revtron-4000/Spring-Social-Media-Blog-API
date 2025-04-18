package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.RegisterException;
import com.example.service.AccountService;
import com.example.service.MessageService;

@Controller
public class AccountController {
    
    private AccountService as;
    private MessageService ms;

    @Autowired
    public AccountController(AccountService as, MessageService ms) {
        this.as = as;
        this.ms = ms;
    }

    @GetMapping("accounts")
    public @ResponseBody List<Account> getAllAccounts() {
        return as.getAllAccounts();
    }

    @GetMapping("accounts/{account_id}/messages")
    public @ResponseBody List<Message> getAllAccountMessages(@PathVariable Integer account_id) {
        return ms.getAccountMessages(account_id);
    }

    @PostMapping("register")
    public @ResponseBody ResponseEntity<Account> createAccount(@RequestBody Account acc) {
        Account persistedAcc = as.register(acc);
        return ResponseEntity.status(200).body(persistedAcc);
    }

    @PostMapping("login")
    public @ResponseBody ResponseEntity<Account> login(@RequestBody Account givenAcc) throws AuthenticationException{
        Account loggedInAccount = as.login(givenAcc);
        System.out.println("Login sucessful!");
        return ResponseEntity.status(200).body(loggedInAccount);
    }





    // Insert Exception Handlers below
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

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ResponseEntity<String> handleUnauthorized(AuthenticationException ex) {
        ResponseEntity<String> re = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        System.out.println(re.getStatusCode());
        return re;
    }
}
