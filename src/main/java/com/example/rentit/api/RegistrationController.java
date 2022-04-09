package com.example.rentit.api;

import com.example.rentit.userservice.registration.RegistrationRequest;
import com.example.rentit.userservice.registration.RegistrationService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 3/13/2022
 */
@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
