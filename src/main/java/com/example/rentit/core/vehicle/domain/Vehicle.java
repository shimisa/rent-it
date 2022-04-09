package com.example.rentit.core.vehicle.domain;

import com.example.rentit.userservice.domain.User;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/2/2022
 */
@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private Long licenseNo;

    @Enumerated(EnumType.STRING)
    private TypeOfVehicle typeOfVehicle;
    private String model;
    private int year;
    @Enumerated(EnumType.STRING)
    private GearType gearType;
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    private String description;

    @ElementCollection
    private Set<CarAccessories> carAccessories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;


    public Vehicle(Long licenseNo,
                   TypeOfVehicle typeOfVehicle,
                   String model,
                   int year,
                   GearType gearType,
                   EngineType engineType,
                   String description,
                   Set<CarAccessories> carAccessories,
                   User owner) {
        this.licenseNo = licenseNo;
        this.typeOfVehicle = typeOfVehicle;
        this.model = model;
        this.year = year;
        this.gearType = gearType;
        this.engineType = engineType;
        this.description = description;
        this.carAccessories = carAccessories;
        this.owner = owner;
    }
}
