package com.example.rentit.userservice.repo;

import com.example.rentit.userservice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 1/16/2022
 */
public interface UserRepo extends JpaRepository<User, Long> {
    //Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findFirst1ByOrderByIdDesc();
    Page<User> findAll(Pageable pageable);
}
