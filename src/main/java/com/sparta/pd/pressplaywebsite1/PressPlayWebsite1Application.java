package com.sparta.pd.pressplaywebsite1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PressPlayWebsite1Application {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(PressPlayWebsite1Application.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(UserRepository userRepository){
        return args -> {
            *//*customerRepository.save(new CustomerEntity("Pawel","Dyjak",1, "email@gmail.com",1,"USER",
                    new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()), "pawel"));*//*
            userRepository.save(new UserEntity("Pawel", encoder.encode("pawel"), "ADMIN", 1));


        };
    }*/

}
