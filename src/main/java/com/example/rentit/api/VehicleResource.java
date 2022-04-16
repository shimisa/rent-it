package com.example.rentit.api;

import com.example.rentit.core.vehicle.service.VehicleService;
import com.example.rentit.core.vehicle.domain.CarAccessories;
import com.example.rentit.core.vehicle.domain.SaveVehicleRequest;
import com.example.rentit.core.vehicle.domain.Vehicle;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/3/2022
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VehicleResource {
    private final VehicleService vehicleService;

//    @GetMapping("/vehicles")
//    public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam(defaultValue = "0") int page) {
//        return ResponseEntity.ok().body(vehicleService.getAllVehicles(page));
//    }
    @GetMapping("/vehiclesofuser")
    public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam String email, @RequestParam(defaultValue = "0" ) int page) {
        return ResponseEntity.ok().body(vehicleService.getVehiclesByOwnerEmail(email ,page));
    }

    @PostMapping("/vehicle/save")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody SaveVehicleRequest saveVehicleRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/vehicle/save").toString());
        return ResponseEntity.created(uri).body(vehicleService.saveVehicle(saveVehicleRequest));
    }

    @PostMapping("/vehicle/addAccessories")
    public ResponseEntity<Vehicle> setAccessoriesToVehicle(@RequestBody AccessoriesToVehicleForm accessoriesToVehicle) {
        vehicleService.setAccessoriesToVehicle(accessoriesToVehicle.getId(), accessoriesToVehicle.getCarAccessories());
        return ResponseEntity.ok().build();
    }
}

@Data
class AccessoriesToVehicleForm {
    private long id;
    private Set<CarAccessories> carAccessories;
}