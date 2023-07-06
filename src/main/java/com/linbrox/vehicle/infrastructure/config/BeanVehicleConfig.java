package com.linbrox.vehicle.infrastructure.config;

import com.linbrox.vehicle.application.service.ExternalAPIService;
import com.linbrox.vehicle.application.service.VehicleService;
import com.linbrox.vehicle.application.service.impl.VehicleServiceImpl;
import com.linbrox.vehicle.domain.repository.VehicleRepository;
import com.linbrox.vehicle.infrastructure.adapter.VehicleRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanVehicleConfig {

    @Bean
    VehicleService vehicleService(
            final VehicleRepository conversionRepository,
            final ExternalAPIService externalAPIService
            ){
        return new VehicleServiceImpl(externalAPIService, conversionRepository);
    }

    @Bean VehicleRepository conversionRepository(VehicleRepositoryAdapter conversionRepositoryAdapter){
        return conversionRepositoryAdapter;
    }

}
