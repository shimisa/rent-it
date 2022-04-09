package com.example.rentit.core.vehicle.repo;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/3/2022
 */
public class VehicleNotFoundException extends IllegalStateException {
    public VehicleNotFoundException(String msg) {
        super(msg);
    }
}
