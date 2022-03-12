package com.example.rentit.userservice.repo;

import com.example.rentit.userservice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 1/16/2022
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findFirst1ByOrderByIdDesc();
    Page<User> findAll(Pageable pageable);
}
