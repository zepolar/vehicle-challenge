package com.linbrox.vehicle.infrastructure.controller;

import com.linbrox.vehicle.application.service.VehicleService;
import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.domain.model.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {
    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @Test
    @DisplayName("Should return list of vehicles when calling findByNameAndModel with valid parameters")
    void shouldReturnListOfVehiclesWhenCallingFindByNameAndModelWithValidParameters() {
        // Prepare test data
        HyundaiModelEnum model = HyundaiModelEnum.ACCENT;
        String name = "Sedan";
        List<Vehicle> expectedVehicles = Arrays.asList(new Vehicle(), new Vehicle());
        // Mock the vehicleService method
        when(vehicleService.findByNameAndModel(name, model)).thenReturn(expectedVehicles);
        // Perform the API call
        Flux<Vehicle> result = vehicleController.findByNameAndModel(model, name);
        // Verify the result
        StepVerifier.create(result)
                .expectNextSequence(expectedVehicles)
                .verifyComplete();

        // Verify that the vehicleService method was called with the correct parameters
        verify(vehicleService).findByNameAndModel(name, model);

    }

    @Test
    @DisplayName("Should return all vehicles when no parameters provided in findByNameAndModel")
    void shouldReturnAllVehiclesWhenNoParametersProvidedInFindByNameAndModel() {
        // Prepare test data
        List<Vehicle> expectedVehicles = Arrays.asList(new Vehicle(), new Vehicle());

        // Mock the vehicleService method
        when(vehicleService.findAll()).thenReturn(expectedVehicles);

        // Perform the API call
        Flux<Vehicle> result = vehicleController.findByNameAndModel(null, null);

        // Verify the result
        StepVerifier.create(result)
                .expectNextSequence(expectedVehicles)
                .verifyComplete();

        // Verify that the vehicleService method was called without any parameters
        verify(vehicleService).findAll();
    }

}
