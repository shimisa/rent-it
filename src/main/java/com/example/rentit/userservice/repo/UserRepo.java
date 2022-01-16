package com.example.rentit.userservice.repo;

import com.example.rentit.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 1/16/2022
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
