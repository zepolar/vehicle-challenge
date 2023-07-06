package com.linbrox.vehicle.application.service;


import com.linbrox.vehicle.application.api.HyundaiAPI;
import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.application.api.response.HyundaiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ExternalAPIService {
    private final HyundaiAPI hyundaiAPI;

    public ExternalAPIService(HyundaiAPI hyundaiAPI) {
        this.hyundaiAPI = hyundaiAPI;
    }

    @CircuitBreaker(name = "externalAPI", fallbackMethod = "fallbackResponse")
    public Flux<HyundaiResponse> callExternalAPI(HyundaiModelEnum hyundaiModel) {
       return this.hyundaiAPI.retrieveVehicles(hyundaiModel);
    }

    private Flux<HyundaiResponse> fallbackResponse(Throwable throwable) {
        // Handle the case when the external API call fails
        return Flux.empty();
    }
}
