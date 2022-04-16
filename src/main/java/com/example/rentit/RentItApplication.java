package com.example.rentit;

import com.example.rentit.core.post.domain.PostRequest;
import com.example.rentit.core.post.service.PostService;
import com.example.rentit.core.vehicle.domain.*;
import com.example.rentit.core.vehicle.service.VehicleService;
import com.example.rentit.userservice.domain.User;
import com.example.rentit.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    CommandLineRunner run(UserService userService, PostService postService, VehicleService vehicleService) {
        return args -> {

            /* inserting users data into DB for testing */

            User superAdminUser = new User("Shimi", "Sadaka", "sh.sadaka@gmail.com","1234", ROLE_SUPER_ADMIN);
            superAdminUser.setEnabled(true);
            userService.saveUser(superAdminUser);


            Set<CarAccessories> carAccessories = Arrays.stream(CarAccessories.values()).collect(Collectors.toSet());

            vehicleService.saveVehicle(new SaveVehicleRequest(
                    1234L,
                    TypeOfVehicle.CAR_4WD,
                    "Jeep", 2006,
                    GearType.AUTOMATIC,
                    EngineType.DIESEL,
                    "Beatiful car!",
                    carAccessories,
                    "sh.sadaka@gmail.com"
            ));

            postService.savePost(new PostRequest(
                    "Jeep for rent",
                    "beutiful Jeep for rent",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(10),
                    1234L
            ));

        };
    }


}
