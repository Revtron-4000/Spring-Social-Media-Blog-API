package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import static org.springframework.boot.SpringApplication.run;

import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

// Delete these imports
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;



/**
 * This is a class that is used to run your application.
 *
 * DO NOT CHANGE ANYTHING IN THIS CLASS
 *
 */
@SpringBootApplication
public class SocialMediaApp {
    /**
     * Runs the application
     * @param args The arguments of the program.
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        // SpringApplication.run(SocialMediaApp.class, args);

        //Delete Everything under this
        ApplicationContext ctx = SpringApplication.run(SocialMediaApp.class, args);
        AccountService as = ctx.getBean(AccountService.class);
        MessageService ms = ctx.getBean(MessageService.class);

        System.out.println("----- TESTING -----");
        System.out.println();
        System.out.println();


        // // Testing Account model
        Account acc1 = new Account("user1", "pass1");
        Account acc2 = new Account("user2", "pass2");
        Account acc3 = new Account("user3", "pass3");

        as.register(acc1);
        as.register(acc2);
        as.register(acc3);

        System.out.println("Looking at accounts...");
        System.out.println(as.getAllAccounts());

        // Testing Message Model
        System.out.println("BEFORE I ADDED MESSAGES");
        System.out.println(ms.getAllMessages());
        Message m1 = new Message(1, "This is user1's first message", 2L);
        Message m2 = new Message(1, "This is user1's second message", 3L);
        Message m3 = new Message(1, "This is user1's third message", 4L);

        ms.createMessage(m1);
        ms.createMessage(m2);
        ms.createMessage(m3);


        System.out.println("AFTER I ADDED MESSAGES");
        System.out.println(ms.getAllMessages());

        System.out.println("THESE ARE ALL OF ACCOUNT_ID=1's MESSAGES");
        System.out.println(ms.getAccountMessages(1));
        System.out.println();
        System.out.println(ms.getAccountMessages(70));
        System.out.println();
        System.out.println(ms.getAccountMessages(9996));


    }

    // @Bean
    // public CommandLineRunner inspectorBean(ApplicationContext ctx) {
    //     return args -> {
    //         System.out.printf("Inspecting the Beans provided by Spring Boot");
    //         System.out.println();
    //         String[] beans = ctx.getBeanDefinitionNames();

    //         for (String bean : beans) {
    //             System.out.println(bean);
    //         }

    //         System.out.println("Ending inspection");
    //     };
    // }
}
