package com.linbrox.vehicle.application.service;

import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.domain.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> migrateVehiclesFromExternal(HyundaiModelEnum hyundaiModel);
    List<Vehicle> findAll();
    List<Vehicle> findByNameAndModel(String name, HyundaiModelEnum modelEnum);
    List<Vehicle> findByModel(HyundaiModelEnum model);
    List<Vehicle> findByName(String name);
}
