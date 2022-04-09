package com.example.rentit.core.feedback.domain;

import com.example.rentit.core.order.domain.Order;
import com.example.rentit.userservice.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
public class FeedbackOnRenter implements Feedback {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    LocalDateTime date;
    FeedbackRating feedbackRating;
    Stars vehicleCleaning;
    Stars communication;
    Stars deliveryTime;
    Stars wouldRentHimAgain;

    @Column(length = 100)
    String comment;

    @OneToOne
    @JoinColumn(name = "order_order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "car_renter_id")
    User carRenter;
}
