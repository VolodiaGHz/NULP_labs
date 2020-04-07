package com.hrynyk.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        AccountGenerator accountGenerator = new AccountGenerator();
//        accountGenerator.generateAccount();

        SpringApplication.run(Application.class,args);
    }
}

