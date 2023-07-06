package com.linbrox.vehicle.infrastructure.adapter;

import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.domain.model.Vehicle;
import com.linbrox.vehicle.domain.repository.VehicleRepository;
import com.linbrox.vehicle.infrastructure.entity.VehicleEntity;
import com.linbrox.vehicle.infrastructure.repository.VehicleRepositorySpringData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class VehicleRepositoryAdapter implements VehicleRepository {

    private final VehicleRepositorySpringData vehicleRepositorySpringData;

    public VehicleRepositoryAdapter(VehicleRepositorySpringData vehicleRepositorySpringData) {
        this.vehicleRepositorySpringData = vehicleRepositorySpringData;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity vehicleEntity = VehicleEntity.fromDomanModel(vehicle);
        return this.vehicleRepositorySpringData.save(vehicleEntity).toDomanModel();
    }

    @Override
    public List<Vehicle> findAll() {
        return this.vehicleRepositorySpringData.findAll()
                .stream().map(VehicleEntity::toDomanModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Vehicle> findById(UUID uuid) {
        return this.vehicleRepositorySpringData.findById(uuid)
                .map(VehicleEntity::toDomanModel);
    }

    @Override
    public List<Vehicle> findByNameAndModel(String name, HyundaiModelEnum modelEnum) {
        return this.vehicleRepositorySpringData.findByHyundaiModelAndAndName(modelEnum, name)
                .stream().map(VehicleEntity::toDomanModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByModel(HyundaiModelEnum model) {
        return this.vehicleRepositorySpringData.findByHyundaiModel(model)
                .stream().map(VehicleEntity::toDomanModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByName(String name) {
        return this.vehicleRepositorySpringData.findByName(name)
                .stream().map(VehicleEntity::toDomanModel)
                .collect(Collectors.toList());
    }
}
