package com.example.rentit.core.vehicle.repo;

import com.example.rentit.core.vehicle.domain.TypeOfVehicle;
import com.example.rentit.core.vehicle.domain.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/2/2022
 */
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicenseNo(long licenseNo);
    Optional<Vehicle> findById(long id);
    Optional<Vehicle> findByOwnerEmail(String ownerUsername);
    Page<Vehicle> findByTypeOfVehicle(TypeOfVehicle typeOfVehicle, Pageable pageable);
    Page<Vehicle> findByYear(int year, Pageable pageable);
    Page<Vehicle> findAll(Pageable pageable);
}
