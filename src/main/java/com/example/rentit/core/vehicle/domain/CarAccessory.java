package com.example.rentit.core.vehicle.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "car_accessory")
public class CarAccessory implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private CarAccessories carAccessory;

    public CarAccessory(CarAccessories accessory) {
        this.carAccessory = accessory;
    }

    public CarAccessory() {

    }
}