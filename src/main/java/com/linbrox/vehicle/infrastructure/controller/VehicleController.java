package com.linbrox.vehicle.infrastructure.controller;

import com.linbrox.vehicle.application.service.VehicleService;
import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.domain.model.Vehicle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Api(tags = "Vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @ApiOperation(value = "Migrate information of vehicles", notes = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Something bad happened")
    })
    @GetMapping("/vehicle/migrate")
    public Flux<Vehicle> migrateVehicleFromExternalApi(@ApiParam(value = "Model")
                                          @RequestParam HyundaiModelEnum modelEnum) {
        return Flux.fromIterable(vehicleService.migrateVehiclesFromExternal(modelEnum));
    }

    @ApiOperation(value = "Retrieve information of vehicles based on name and model", notes = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Something bad happened")
    })
    @GetMapping("/vehicle/list")
    public Flux<Vehicle> findByNameAndModel(
            @ApiParam(value = "Model")
            @RequestParam(required = false) HyundaiModelEnum model,
            @ApiParam(value = "Name")
            @RequestParam(required = false) String name
        ) {
        if (model == null && name == null) {
            // No parameters provided, retrieve all vehicles
            return Flux.fromIterable(vehicleService.findAll());
        }
        if(model == null && StringUtils.isNotEmpty(name)){
            return Flux.fromIterable(vehicleService.findByName(name));
        }
        if(model !=null && StringUtils.isEmpty(name)){
            return Flux.fromIterable(vehicleService.findByModel(model));
        }
        else {
            // Parameters provided, filter vehicles by model and name
            return Flux.fromIterable(vehicleService.findByNameAndModel(name, model));
        }
    }

}