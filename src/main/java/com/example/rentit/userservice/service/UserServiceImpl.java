package com.example.rentit.userservice.service;

import com.example.rentit.userservice.domain.Role;
import com.example.rentit.userservice.domain.RoleName;
import com.example.rentit.userservice.domain.User;
import com.example.rentit.userservice.registration.token.ConfirmationToken;
import com.example.rentit.userservice.registration.token.ConfirmationTokenService;
import com.example.rentit.userservice.repo.UserRepo;
import com.example.rentit.userservice.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 1/16/2022
 */
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final int MAX_USERS_FOR_PAGE = 2;
    private static final int CONFIRMATION_TOKEN_EXPIRY_TIME = 15;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    /**
     * configured the authorities of the loaded user
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow( () -> {
                    log.error("User not found in the database");
                    throw new UsernameNotFoundException("User not found in the database");
                });

        log.info("User found in the database: {}", email);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, RoleName roleName) {
        log.info("Adding role  {} to user {}", roleName, email);
        User user = userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found in the database"));
        user.setRole(roleName);
    }

    @Override
    public User getUser(String email) {
        log.info("Fetching user {}", email);
        return userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found in the database"));
    }

    @Override
    public List<User> getUsers(int page) {
        Pageable pageable = PageRequest.of(page, MAX_USERS_FOR_PAGE);
        return userRepo.findAll(pageable).getContent();
    }

    @Override
    public String signUpUser(User user) {
        boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            log.error("email already taken");
            throw new IllegalStateException("email already taken");
        }
        saveUser(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(CONFIRMATION_TOKEN_EXPIRY_TIME),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: send email

        return token;
    }

    @Override
    public void enableUser(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User not found")
        );
        user.setEnabled(true);
    }


}
