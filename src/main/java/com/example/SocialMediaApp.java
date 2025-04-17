package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.MessageService;

import static org.springframework.boot.SpringApplication.run;

import java.util.Arrays;

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
        Account acc1 = new Account("user1", "pass1");
        Account acc2 = new Account("user2", "pass2");
        Account acc3 = new Account("user3", "pass3");

        // Invalid Accounts
        // Account acc4 = new Account("    ", "password4");
        // Account acc5 = new Account("user5", "pw2");
        Account acc6 = new Account("testuser1", "pw21");


        as.register(acc1);
        as.register(acc2);
        as.register(acc3);

        // as.register(acc4);
        // as.register(acc5);
        // as.register(acc6);

        System.out.println(as.getAllAccounts());
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
