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
public class RatingOfRenter implements Rating {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private long numOfVotes;

    double percentage_positiveFeedback; // Positives / (Positives + Negatives)

    private long numOfPositives;
    private long numOfNegatives;
    private long numOfNeutrals;

    private double avg_vehicleCleaning;
    private double avg_communication;
    private double avg_deliveryTime;
    private double avg_wouldRentHimAgain;


    @JoinColumn(name = "car_renter_id")
    @OneToOne
    User carRenter;

    public RatingOfRenter(User carRenter) {
        this.carRenter = carRenter;
    }
}
