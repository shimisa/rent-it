package com.example.rentit.core.feedback.service;

import com.example.rentit.core.feedback.domain.FeedbackOnRental;
import com.example.rentit.core.feedback.domain.FeedbackOnRenter;
import com.example.rentit.core.feedback.repo.FeedbackOnRentalRepo;
import com.example.rentit.core.feedback.repo.FeedbackOnRenterRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackOnRentalRepo feedbackOnRentalRepo;
    private final FeedbackOnRenterRepo feedbackOnRenterRepo;
    private static final int MAX_FEEDBACKS_PER_PAGE = 20;


    @Override
    public List<FeedbackOnRental> getFeedbackOnRentalList(int page, long id) {
        log.info("Fetching list of feedbacks for car rental id: {}", id);
        Pageable pageable = PageRequest.of(page, MAX_FEEDBACKS_PER_PAGE);
        return feedbackOnRentalRepo.findByCarRentalId(id, pageable).orElseThrow(
                () -> new IllegalArgumentException("There is no feedbacks for this rental")
        );
    }

    @Override
    public List<FeedbackOnRenter> getFeedbackOnRenterList(int page, long id) {
        log.info("Fetching list of feedbacks for car renter id: {}", id);
        Pageable pageable = PageRequest.of(page, MAX_FEEDBACKS_PER_PAGE);
        return feedbackOnRenterRepo.findByCarRenterId(id, pageable).orElseThrow(
                () -> new IllegalArgumentException("There is no feedbacks for this renter")
        );
    }
}
