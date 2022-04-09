package com.example.rentit.core.feedback.repo;

import com.example.rentit.core.feedback.domain.RatingOfRenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
public interface RatingOfRenterRepo extends JpaRepository<RatingOfRenter, Long> {
    Optional<RatingOfRenter> findByCarRenterId(long id);

}
