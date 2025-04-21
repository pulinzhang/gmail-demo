package com.pubing.gmaildemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pubing.**")
public class GmailDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailDemoApplication.class, args);
    }

}
