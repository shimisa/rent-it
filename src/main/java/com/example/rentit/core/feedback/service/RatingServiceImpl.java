package com.example.rentit.core.feedback.service;

import com.example.rentit.core.feedback.domain.*;
import com.example.rentit.core.feedback.repo.RatingOfRentalRepo;
import com.example.rentit.core.feedback.repo.RatingOfRenterRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 *
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingOfRentalRepo ratingOfRentalRepo;
    private final RatingOfRenterRepo ratingOfRenterRepo;


    @Override
    public double calcPositiveFeedback(double numOfPositives, double numOfNegatives) {
        double result;
        try {
            result = numOfPositives / (numOfPositives + numOfNegatives);
        } catch (Exception e){
            return 0;
        }
        return result;
    }

    @Override
    public double calcAvgStarRating(double currentAvg, Stars newRating, long numOfVotes) {
        return (currentAvg + newRating.val()) / (numOfVotes + 1);
    }

    @Override
    public void updateRatingOfRental(FeedbackOnRental feedback) {
        Optional<RatingOfRental> optionalCurrentRating = ratingOfRentalRepo.findByCarRentalId(feedback.getCarRental().getId());
        RatingOfRental ratingOfRental;
        if (optionalCurrentRating.isEmpty()) {
            ratingOfRental = new RatingOfRental(feedback.getCarRental());
        } else {
            ratingOfRental = optionalCurrentRating.get();
        }
        appendNumOfPositives(feedback.getFeedbackRating(), ratingOfRental);
        double percentage_positiveFeedback = calcPositiveFeedback(ratingOfRental.getNumOfPositives(), ratingOfRental.getNumOfNegatives());
        ratingOfRental.setPercentage_positiveFeedback(percentage_positiveFeedback);
        long numOfVotes = ratingOfRental.getNumOfVotes();
        ratingOfRental.setAvg_carAsDescribed(calcAvgStarRating(ratingOfRental.getAvg_carAsDescribed(), feedback.getCarAsDescribed(), numOfVotes));
        ratingOfRental.setAvg_communication(calcAvgStarRating(ratingOfRental.getAvg_communication(), feedback.getCommunication(), numOfVotes));
        ratingOfRental.setAvg_deliveryTime(calcAvgStarRating(ratingOfRental.getAvg_deliveryTime(), feedback.getDeliveryTime(), numOfVotes));
        ratingOfRental.setAvg_wouldRecommendToFriend(calcAvgStarRating(ratingOfRental.getAvg_wouldRecommendToFriend(), feedback.getWouldRecommendToFriend(), numOfVotes));
        ratingOfRental.setNumOfVotes(numOfVotes + 1);
        ratingOfRentalRepo.save(ratingOfRental);
    }


    @Override
    public void updateRatingOfRenter(FeedbackOnRenter feedback) {
        Optional<RatingOfRenter> optionalCurrentRating = ratingOfRenterRepo.findByCarRenterId(feedback.getCarRenter().getId());
        RatingOfRenter ratingOfRenter;
        if (optionalCurrentRating.isEmpty()) {
            ratingOfRenter = new RatingOfRenter(feedback.getCarRenter());
        } else {
            ratingOfRenter = optionalCurrentRating.get();
        }
        appendNumOfPositives(feedback.getFeedbackRating(), ratingOfRenter);
        double percentage_positiveFeedback = calcPositiveFeedback(ratingOfRenter.getNumOfPositives(), ratingOfRenter.getNumOfNegatives());
        ratingOfRenter.setPercentage_positiveFeedback(percentage_positiveFeedback);
        long numOfVotes = ratingOfRenter.getNumOfVotes();
        ratingOfRenter.setAvg_vehicleCleaning(calcAvgStarRating(ratingOfRenter.getAvg_vehicleCleaning(), feedback.getVehicleCleaning(), numOfVotes));
        ratingOfRenter.setAvg_communication(calcAvgStarRating(ratingOfRenter.getAvg_communication(), feedback.getCommunication(), numOfVotes));
        ratingOfRenter.setAvg_deliveryTime(calcAvgStarRating(ratingOfRenter.getAvg_deliveryTime(), feedback.getDeliveryTime(), numOfVotes));
        ratingOfRenter.setAvg_wouldRentHimAgain(calcAvgStarRating(ratingOfRenter.getAvg_wouldRentHimAgain(), feedback.getWouldRentHimAgain(), numOfVotes));
        ratingOfRenter.setNumOfVotes(numOfVotes + 1);
        ratingOfRenterRepo.save(ratingOfRenter);
    }

    @Override
    public RatingOfRental getRatingOfRental(long carRentalId) {
        log.info("Fetching RatingOfRental by carRentalId: {}", carRentalId);
        return ratingOfRentalRepo.findByCarRentalId(carRentalId).orElseThrow(
                () -> new IllegalStateException("RatingOfRental not found ")
        );
    }

    @Override
    public RatingOfRenter getRatingOfRenter(long carRenterId) {
        log.info("Fetching RatingOfRenter by carRenterId: {}", carRenterId);
        return ratingOfRenterRepo.findByCarRenterId(carRenterId).orElseThrow(
                () -> new IllegalStateException("RatingOfRenter not found ")
        );
    }

    private void appendNumOfPositives(FeedbackRating feedbackRating, Rating rating) {
        switch (feedbackRating) {
            case POSITIVE:
                rating.setNumOfPositives(rating.getNumOfPositives() + 1);
                break;
            case NEGATIVE:
                rating.setNumOfNegatives(rating.getNumOfNegatives() + 1);
                break;
            case NEUTRAL:
                rating.setNumOfNeutrals(rating.getNumOfNeutrals() + 1);
                break;
        }
    }


}
