package com.example.rentit.core.feedback.repo;

import com.example.rentit.core.feedback.domain.FeedbackOnRenter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
public interface FeedbackOnRenterRepo extends JpaRepository<FeedbackOnRenter, Long> {
    Optional<List<FeedbackOnRenter>> findByCarRenterId(long id, Pageable pageable);

}
