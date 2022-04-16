package com.example.rentit.api;

import com.example.rentit.userservice.registration.RegistrationRequest;
import com.example.rentit.userservice.registration.RegistrationResponse;
import com.example.rentit.userservice.registration.RegistrationService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toString());
        return ResponseEntity.created(uri).body(registrationService.register(request));
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token){
        return ResponseEntity.ok().body(registrationService.confirmToken(token));
    }
}
