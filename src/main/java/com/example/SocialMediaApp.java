package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

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

        MessageService ms = ctx.getBean(MessageService.class);
        System.out.println(ms);
    }

    @Bean
    public CommandLineRunner inspectorBean(ApplicationContext ctx) {
        return args -> {
            System.out.printf("Inspecting the Beans provided by Spring Boot");
            System.out.println();
            String[] beans = ctx.getBeanDefinitionNames();

            for (String bean : beans) {
                System.out.println(bean);
            }

            System.out.println("Ending inspection");
        };
    }


}
