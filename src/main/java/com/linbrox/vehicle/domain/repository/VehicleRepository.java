package com.linbrox.vehicle.domain.repository;

import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    List<Vehicle> findAll();
    Optional<Vehicle> findById(UUID uuid);
    List<Vehicle> findByNameAndModel(String name, HyundaiModelEnum modelEnum);
    List<Vehicle> findByModel(HyundaiModelEnum model);
    List<Vehicle> findByName(String name);

}
