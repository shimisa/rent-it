package com.example.rentit.core.feedback.service;

import com.example.rentit.core.feedback.domain.FeedbackOnRental;
import com.example.rentit.core.feedback.domain.FeedbackOnRenter;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
public interface FeedbackService {
    List<FeedbackOnRental> getFeedbackOnRentalList(int page, long id);
    List<FeedbackOnRenter> getFeedbackOnRenterList(int page, long id);
}
