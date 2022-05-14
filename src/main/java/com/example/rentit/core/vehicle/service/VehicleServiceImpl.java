package com.example.rentit.core.vehicle.service;

import com.example.rentit.core.vehicle.domain.CarAccessories;
import com.example.rentit.core.vehicle.domain.SaveVehicleRequest;
import com.example.rentit.core.vehicle.domain.TypeOfVehicle;
import com.example.rentit.core.vehicle.domain.Vehicle;
import com.example.rentit.core.vehicle.repo.VehicleNotFoundException;
import com.example.rentit.core.vehicle.repo.VehicleRepo;
import com.example.rentit.userservice.domain.User;
import com.example.rentit.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/3/2022
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepo vehicleRepo;
    private final UserRepo userRepo;
    private static final int MAX_VEHICLES_PER_PAGE = 20;


    @Override
    public Vehicle saveVehicle(SaveVehicleRequest saveVehicleRequest) {
        Optional<Vehicle> vehicleOptional = vehicleRepo.findByLicenseNo(saveVehicleRequest.getLicenseNo());
        Vehicle vehicle = null;
        if (vehicleOptional.isPresent()) {
            vehicle = vehicleOptional.get();
            if (!vehicle.isActive()) {  // if present and not active -> make it active
                vehicle.setActive(true);
                return vehicleRepo.save(vehicle);
            }
            log.warn("Vehicle is already exists and active. LicenseNo: {}", saveVehicleRequest.getLicenseNo());
            throw new IllegalStateException("Vehicle is already exists. LicenseNo: " + saveVehicleRequest.getLicenseNo());
        }
        User vehicleOwner =  userRepo.findByEmail(saveVehicleRequest.getOwnerUserName()).orElseThrow(() ->
                new IllegalStateException("User owner vehicle is not exists"));
        vehicle = new Vehicle(
                saveVehicleRequest.getLicenseNo(),
                saveVehicleRequest.getTypeOfVehicle(),
                saveVehicleRequest.getModel(),
                saveVehicleRequest.getYear(),
                saveVehicleRequest.getGearType(),
                saveVehicleRequest.getEngineType(),
                saveVehicleRequest.getDescription(),
                saveVehicleRequest.getCarAccessories(),
                vehicleOwner
        );
        log.info("Saving new vehicle to the database: " +
                "\n Type: {} LicenseNo: {}", saveVehicleRequest.getLicenseNo(), saveVehicleRequest.getLicenseNo());
        return vehicleRepo.save(vehicle);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        log.info("Inactive Vehicle id: {}", vehicleId);
        Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow();
        vehicle.setActive(false);
        vehicleRepo.save(vehicle);
    }

    @Override
    public void setAccessoriesToVehicle(long id, Collection<CarAccessories> carAccessories) {
        log.info("Setting Accessories To Vehicle id: {}", id);
        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(() ->
                new VehicleNotFoundException("Vehicle not found in the database"));
        Set<CarAccessories> vehicleCarAccessories = vehicle.getCarAccessories();
        vehicleCarAccessories.clear();
        vehicleCarAccessories.addAll(carAccessories);
        vehicleRepo.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(long id) {
        log.info("Fetching vehicle by id {}", id);
        return vehicleRepo.findById(id).orElseThrow(() ->
                new VehicleNotFoundException("Vehicle not found in the database"));
    }

    @Override
    public Vehicle getVehicleByLicenseNo(long licenseNo) {
        log.info("Fetching vehicle by licenseNo {}", licenseNo);
        return vehicleRepo.findByLicenseNo(licenseNo).orElseThrow(() ->
                new VehicleNotFoundException("Vehicle not found in the database"));
    }

    @Override
    public List<Vehicle> getAllVehicles(int page) {
        log.info("Fetching All vehicles from page {}", page);
        Pageable pageable = PageRequest.of(page, MAX_VEHICLES_PER_PAGE);
        return vehicleRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Vehicle> getVehiclesByYear(int year, int page) {
        log.info("Fetching vehicle by Year from page {}", page);
        Pageable pageable = PageRequest.of(page, MAX_VEHICLES_PER_PAGE);
        return vehicleRepo.findByYear(year, pageable).getContent();
    }

    @Override
    public List<Vehicle> getVehiclesByType(TypeOfVehicle typeOfVehicle, int page) {
        log.info("Fetching vehicle by Type from page {}", page);
        Pageable pageable = PageRequest.of(page, MAX_VEHICLES_PER_PAGE);
        return vehicleRepo.findByTypeOfVehicle(typeOfVehicle, pageable).getContent();
    }

    @Override
    public List<Vehicle> getVehiclesByOwnerEmail(String email, int page) {
        log.info("Fetching vehicle by Owner Email from page {}", page);
        Pageable pageable = PageRequest.of(page, MAX_VEHICLES_PER_PAGE);
        return vehicleRepo.findByOwnerEmail(email, pageable).stream()
                .filter(Vehicle::isActive).collect(toList());
    }
}