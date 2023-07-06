package com.linbrox.vehicle.application.service.impl;

import com.linbrox.vehicle.application.service.ExternalAPIService;
import com.linbrox.vehicle.application.service.VehicleService;
import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.domain.model.Vehicle;
import com.linbrox.vehicle.domain.repository.VehicleRepository;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    private final ExternalAPIService externalAPIService;
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(ExternalAPIService externalAPIService, VehicleRepository vehicleRepository) {
        this.externalAPIService = externalAPIService;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> migrateVehiclesFromExternal(HyundaiModelEnum hyundaiModel) {
        var vehicleList = this.vehicleRepository.findByModel(hyundaiModel);
        if (vehicleList.isEmpty()) {
            this.externalAPIService.callExternalAPI(hyundaiModel)
                    .doOnNext(element -> {
                        var vehicle = Vehicle.builder()
                                .code(element.getCode())
                                .name(element.getName())
                                .year(element.getYear())
                                .salePrice(element.getSalePrice().doubleValue())
                                .bonus(element.getBonus().doubleValue())
                                .finalPrice(element.getFinalPrice().doubleValue())
                                .disability100(element.getDisability100())
                                .hyundaiModel(hyundaiModel)
                                .sgcCode(element.getSgcCode())
                                .build();
                        this.vehicleRepository.save(vehicle);
                        vehicleList.add(vehicle);
                    })
                    .doOnComplete(() -> {
                        System.out.println("Flux is empty");
                    })
                    .blockLast(); // Wait for the completion of the Flux
            return vehicleList;
        } else {
            return vehicleList;
        }
    }

    @Override
    public List<Vehicle> findAll() {
        return this.vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findByNameAndModel(String name, HyundaiModelEnum modelEnum) {
        return this.vehicleRepository.findByNameAndModel(name, modelEnum);
    }

    @Override
    public List<Vehicle> findByModel(HyundaiModelEnum model) {

        return this.vehicleRepository.findByModel(model);
    }

    @Override
    public List<Vehicle> findByName(String name) {
        return  this.vehicleRepository.findByName(name);
    }
}
