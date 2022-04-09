package com.example.rentit.core.vehicle.service;

import com.example.rentit.core.vehicle.domain.CarAccessories;
import com.example.rentit.core.vehicle.domain.SaveVehicleRequest;
import com.example.rentit.core.vehicle.domain.TypeOfVehicle;
import com.example.rentit.core.vehicle.domain.Vehicle;

import java.util.Collection;
import java.util.List;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/2/2022
 */
public interface VehicleService {
    Vehicle saveVehicle(SaveVehicleRequest saveVehicleRequest);
    void setAccessoriesToVehicle(long id, Collection<CarAccessories> carAccessories);
    Vehicle getVehicleById(long id);
    Vehicle getVehicleByLicenseNo(long licenseNo);
    List<Vehicle> getAllVehicles(int page);
    List<Vehicle> getVehiclesByYear(int year, int page);
    List<Vehicle> getVehiclesByType(TypeOfVehicle typeOfVehicle, int page);


}
