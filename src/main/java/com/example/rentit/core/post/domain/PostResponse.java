package com.example.rentit.core.post.domain;

import com.example.rentit.core.feedback.domain.RatingOfRental;
import com.example.rentit.core.vehicle.domain.CarAccessories;
import com.example.rentit.core.vehicle.domain.EngineType;
import com.example.rentit.core.vehicle.domain.GearType;
import com.example.rentit.core.vehicle.domain.TypeOfVehicle;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/12/2022
 */
@Data
@AllArgsConstructor
public class PostResponse {

    private Long postId;
    private LocalDateTime postedAt;
    private String header;
    private String description;
    private LocalDateTime fromDate;
    private LocalDateTime tillDate;
    private TypeOfVehicle typeOfVehicle;
    private String model;
    private int year;
    @Enumerated(EnumType.STRING)
    private GearType gearType;
    @Enumerated(EnumType.STRING)
    private EngineType engineType;
    private String carDescription;
    @ElementCollection
    private Set<CarAccessories> carAccessories;
    private String rentalFirstName;
    private RatingOfRental ratingOfRental;

}
