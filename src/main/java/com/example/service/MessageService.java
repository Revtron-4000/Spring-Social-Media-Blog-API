package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.MessageException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepo;
    private final AccountRepository accountRepo;

    @Autowired
    public MessageService(MessageRepository messageRepo, AccountRepository accountRepo) {
        this.messageRepo = messageRepo;
        this.accountRepo = accountRepo;
    }

    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    public Message createMessage(Message givenMessage) {
        if (givenMessage.getMessageText().isBlank()) {
            throw new MessageException("This message is blank");
        }

        if (givenMessage.getMessageText().length() >= 255) {
            throw new MessageException("This message is 255 characters or more.");
        }

        Optional<Account> acc = accountRepo.findById(givenMessage.getPostedBy());
        if (!acc.isPresent()) {
            throw new MessageException("This account doesn't exist.");
        }

        return messageRepo.save(givenMessage);
    }

    public Message findMessageById(Integer id) {
        Optional<Message> foundMessage = messageRepo.findById(id);
        
        if (foundMessage.isPresent()) {
            return foundMessage.get();
        } else {
            return null;
        }
    }

    public Integer deleteMessageById(Integer id) {
        Optional<Message> foundMessage = messageRepo.findById(id);

        if (foundMessage.isPresent()) {
            messageRepo.deleteById(id);
            return 1;
        } else {
            return null;
        }
    }

    public Integer updateMessageById(Integer id, Message givenMessage) {
        Optional<Message> foundMessage = messageRepo.findById(id);

        if (foundMessage.isPresent()) {
            Message oldMessage = foundMessage.get();
            String newMessageText = givenMessage.getMessageText();

            if (newMessageText.isBlank()) {
                throw new MessageException("This new message is blank.");
            }

            if (newMessageText.length() > 255) {
                throw new MessageException("This message is over 255 characters");
            }

            oldMessage.setMessageText(newMessageText);
            messageRepo.save(oldMessage);
            return 1;
        } else {
            throw new MessageException("No such message_id exists");
        }
    }
}
