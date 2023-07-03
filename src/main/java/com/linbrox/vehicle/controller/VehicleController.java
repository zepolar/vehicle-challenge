package com.linbrox.vehicle.controller;

import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.entity.Vehicle;
import com.linbrox.vehicle.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Vehicle> migrateVehicleFromExternalApi(@ApiParam(value = "Model")
                                          @RequestParam HyundaiModelEnum modelEnum) {
        return vehicleService.migrateVehiclesFromExternal(modelEnum);
    }

    @ApiOperation(value = "Retrieve information of vehicles based on name and model", notes = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Something bad happened")
    })
    @GetMapping("/vehicle/list")
    public List<Vehicle> findByNameAndModel(
            @ApiParam(value = "Model")
            @RequestParam(required = false) HyundaiModelEnum model,
            @ApiParam(value = "Name")
            @RequestParam(required = false) String name
        ) {
        if (model == null && name == null) {
            // No parameters provided, retrieve all vehicles
            return vehicleService.findAll();
        }
        if(model == null && StringUtils.isNotEmpty(name)){
            return vehicleService.findByName(name);
        }
        if(model !=null && StringUtils.isEmpty(name)){
            return vehicleService.findByModel(model);
        }
        else {
            // Parameters provided, filter vehicles by model and name
            return vehicleService.findByNameAndModel(name, model);
        }
    }

}