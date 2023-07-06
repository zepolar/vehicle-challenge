package com.linbrox.vehicle.infrastructure.repository;

import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.infrastructure.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepositorySpringData extends JpaRepository<VehicleEntity, UUID> {
    List<VehicleEntity> findByHyundaiModel(HyundaiModelEnum hyundaiModel);

    List<VehicleEntity> findByName(String name);

    List<VehicleEntity> findByHyundaiModelAndAndName(HyundaiModelEnum hyundaiModelEnum, String name);
}
