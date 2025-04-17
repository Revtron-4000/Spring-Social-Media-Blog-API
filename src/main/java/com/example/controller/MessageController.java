package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.entity.Message;
import com.example.exception.MessageException;
import com.example.service.MessageService;

@Controller
public class MessageController {
    private MessageService ms;

    @Autowired
    public MessageController(MessageService ms) {
        this.ms = ms;
    }

    @GetMapping("messages")
    public @ResponseBody List<Message> getAllMessages() {
        return ms.getAllMessages();
    }

    @PostMapping("messages")
    public @ResponseBody ResponseEntity<Message> createMessage(@RequestBody Message givenMessage) {
        Message createdMessage = ms.createMessage(givenMessage);
        return ResponseEntity.status(HttpStatus.OK).body(createdMessage);
    }

    @ExceptionHandler(MessageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<String> handleMessageException(MessageException ex) {
        ResponseEntity<String> re = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        System.out.println(re.getStatusCode());
        return re;
    }
}
