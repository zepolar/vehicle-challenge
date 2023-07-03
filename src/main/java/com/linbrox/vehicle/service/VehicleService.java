package com.linbrox.vehicle.service;


import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.entity.Vehicle;
import com.linbrox.vehicle.hyundai.HyundaiService;
import com.linbrox.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final HyundaiService hyundaiService;
    public VehicleService(VehicleRepository vehicleRepository, HyundaiService hyundaiService){
        this.vehicleRepository = vehicleRepository;
        this.hyundaiService = hyundaiService;
    }
    public List<Vehicle> migrateVehiclesFromExternal(HyundaiModelEnum hyundaiModel){
        var vehicleList = this.vehicleRepository.findByHyundaiModel(hyundaiModel);
        if (vehicleList.isEmpty()) {
            this.hyundaiService.callExternalAPI(hyundaiModel)
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

    public List<Vehicle> findAll(){
        return this.vehicleRepository.findAll();
    }

    public List<Vehicle> findByNameAndModel(String name, HyundaiModelEnum modelEnum){
        return this.vehicleRepository.findByHyundaiModelAndAndName(modelEnum, name);
    }

    public List<Vehicle> findByModel(HyundaiModelEnum model){
        return this.vehicleRepository.findByHyundaiModel(model);
    }
    public List<Vehicle> findByName(String name){
        return this.vehicleRepository.findByName(name);
    }

}
