package com.example.rentit.core.vehicle.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/3/2022
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SaveVehicleRequest {
    @NotNull
    private Long licenseNo;
    @NotNull
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
    private Set<CarAccessories> carAccessories;
    @NotNull
    private String ownerUserName;
}
