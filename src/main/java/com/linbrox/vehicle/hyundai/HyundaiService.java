package com.linbrox.vehicle.hyundai;


import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.hyundai.response.HyundaiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class HyundaiService {
    private final HyundaiAPI hyundaiAPI;

    public HyundaiService(HyundaiAPI hyundaiAPI) {
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
