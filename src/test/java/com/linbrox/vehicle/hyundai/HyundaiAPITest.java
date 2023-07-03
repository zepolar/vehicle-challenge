package com.linbrox.vehicle.hyundai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linbrox.vehicle.common.HyundaiModelEnum;
import com.linbrox.vehicle.hyundai.response.HyundaiResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class HyundaiAPITest {
    public static MockWebServer mockBackEnd;
    private ObjectMapper MAPPER = new ObjectMapper();
    private HyundaiAPI hyundaiAPI;

    @BeforeAll
    static void setUp( ) throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown( ) throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize( ) {
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        hyundaiAPI = new HyundaiAPI((baseUrl));
    }


    @Test
    void testRetrieveVehicles_CircuitBreakerOpen() {
        // Set up a mock response with an error status code
        MockResponse mockResponse = new MockResponse().setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        mockBackEnd.enqueue(mockResponse);

        // Execute the retrieveVehicles method and verify the behavior
        Flux<HyundaiResponse> responseFlux = hyundaiAPI.retrieveVehicles(HyundaiModelEnum.ACCENT);

        StepVerifier.create(responseFlux)
                .expectError()
                .verify();
    }

    @Test
    void testRetrieveVehicles_Success() {
        // Create a mock response with a success status code and a JSON body
        // Create a mock response with a success status code and the JSON body
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpStatus.OK.value())
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .setBody("[{\"VER_CODIGO\": 1036,\"VER_NOMBRE\": \"ALL NEW ACCENT L\",\"VEA_ANIO\": 2023,\"VEA_PRECIO_PVP\": 18990,\"VEA_BONO\": 0,\"VEA_PRECIO_FINAL\": 18990,\"VEA_DISCAPACIDAD_100\": 0,\"VER_COD_SGC\": \"40\"}]");

        // Enqueue the mock response
        mockBackEnd.enqueue(mockResponse);

        // Execute the retrieveVehicles method and verify the behavior
        Flux<HyundaiResponse> responseFlux = hyundaiAPI.retrieveVehicles(HyundaiModelEnum.ACCENT);

        List<HyundaiResponse> expectedResponses = Arrays.asList(
                HyundaiResponse.builder().code(1036L).name("ALL NEW ACCENT L").year(2023L).salePrice(18990L).bonus(0L).finalPrice(18990L).disability100(0L).sgcCode("40").build()
        );

        StepVerifier.create(responseFlux)
                .expectNextMatches(expectedResponses::contains)
                .expectComplete()
                .verify();
    }
}