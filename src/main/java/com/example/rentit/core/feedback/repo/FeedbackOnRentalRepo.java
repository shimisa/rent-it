package com.example.rentit.core.feedback.repo;

import com.example.rentit.core.feedback.domain.FeedbackOnRental;
import com.example.rentit.userservice.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
public interface FeedbackOnRentalRepo extends JpaRepository<FeedbackOnRental, Long> {
    Optional<List<FeedbackOnRental>> findByCarRentalId(long id, Pageable pageable);
}
