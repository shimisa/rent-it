package com.example.rentit.core.feedback.domain;

import com.example.rentit.userservice.domain.User;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/4/2022
 */
@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RatingOfRental implements Rating {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private long numOfVotes;

    double percentage_positiveFeedback; // Positives / (Positives + Negatives)

    private long numOfPositives;
    private long numOfNegatives;
    private long numOfNeutrals;

    private double avg_carAsDescribed;
    private double avg_communication;
    private double avg_deliveryTime;
    private double avg_wouldRecommendToFriend;

    @JoinColumn(name = "car_rental_id")
    @OneToOne
    User carRental;

    public RatingOfRental(long numOfVotes,
                          double percentage_positiveFeedback,
                          long numOfPositives,
                          long numOfNegatives,
                          long numOfNeutrals,
                          double avg_carAsDescribed,
                          double avg_communication,
                          double avg_deliveryTime,
                          double avg_wouldRecommendToFriend,
                          User carRental) {
        this.numOfVotes = numOfVotes;
        this.percentage_positiveFeedback = percentage_positiveFeedback;
        this.numOfPositives = numOfPositives;
        this.numOfNegatives = numOfNegatives;
        this.numOfNeutrals = numOfNeutrals;
        this.avg_carAsDescribed = avg_carAsDescribed;
        this.avg_communication = avg_communication;
        this.avg_deliveryTime = avg_deliveryTime;
        this.avg_wouldRecommendToFriend = avg_wouldRecommendToFriend;
        this.carRental = carRental;
    }

    public RatingOfRental(User carRental) {
        this.carRental = carRental;
    }
}
