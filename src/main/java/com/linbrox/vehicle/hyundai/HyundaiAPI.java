package com.linbrox.vehicle.hyundai;


import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.hyundai.response.HyundaiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@Component
public class HyundaiAPI {
    private final Logger logger = Logger.getLogger(HyundaiAPI.class.getName());

    private final WebClient webClient;

    @Autowired
    public HyundaiAPI(WebClient.Builder builder, Environment env) {
        String baseURL = env.getProperty("hyundai.api");
        logger.info("Creating VehicleAPI with base "+baseURL);
        this.webClient = builder.baseUrl(baseURL).build();
    }

    public HyundaiAPI(String baseURL){
        logger.info("Creating VehicleAPI via BaseURL");
        this.webClient = WebClient.create(baseURL);
    }

    public Flux<HyundaiResponse> retrieveVehicles(HyundaiModelEnum hyundaiModel){
        logger.info("Retrieving vehicles: "+hyundaiModel);
        return webClient.get()
                .uri("/"+hyundaiModel.getModelNumber())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(HyundaiResponse.class);
    }

}
