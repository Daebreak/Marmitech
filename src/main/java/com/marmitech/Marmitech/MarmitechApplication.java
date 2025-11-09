package com.marmitech.Marmitech;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MarmitechApplication {

    public static void main(String[] args) {
        SpringApplication.run( MarmitechApplication.class, args );
    }

}
