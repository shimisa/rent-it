package com.example.rentit;

import com.example.rentit.userservice.domain.Role;
import com.example.rentit.userservice.domain.RoleName;
import com.example.rentit.userservice.domain.User;
import com.example.rentit.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static com.example.rentit.userservice.domain.RoleName.*;

@SpringBootApplication
public class RentItApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentItApplication.class, args);
    }


    /* generate a bean of BCryptPasswordEncoder */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /* inserting data into DB for testing */
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {

            /* inserting users data into DB for testing */
            userService.saveRole(new Role(null, ROLE_USER));
            userService.saveRole(new Role(null, ROLE_ADMIN));

            userService.saveUser(new User(null, "Jon Travolta", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Will Smith", "will", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jim Carry", "jim", "1234", new ArrayList<>()));

            userService.addRoleToUser("john", ROLE_USER);
            userService.addRoleToUser("will", ROLE_USER);
            userService.addRoleToUser("jim", ROLE_ADMIN);
        };
    }
}
