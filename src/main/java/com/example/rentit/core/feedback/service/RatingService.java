package com.example.rentit.core.feedback.service;

import com.example.rentit.core.feedback.domain.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
public interface RatingService {
    double calcPositiveFeedback(double numOfPositives, double numOfNegatives);  // Positives / (Positives + Negatives)
    double calcAvgStarRating(double currentAvg, Stars newRating, long numOfVotes);  // (currentAvg * numOfVotes + newRating) / (numOfVotes + 1)
    void updateRatingOfRental(FeedbackOnRental feedback);
    void updateRatingOfRenter(FeedbackOnRenter feedback);

    RatingOfRental getRatingOfRental(long carRentalId);
    RatingOfRenter getRatingOfRenter(long carRenterId);



}
