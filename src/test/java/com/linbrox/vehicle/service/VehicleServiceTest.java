package com.linbrox.vehicle.service;

import com.linbrox.vehicle.hyundai.HyundaiService;
import com.linbrox.vehicle.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VehicleServiceTest {

    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private HyundaiService hyundaiService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleService(vehicleRepository, hyundaiService);
    }


}