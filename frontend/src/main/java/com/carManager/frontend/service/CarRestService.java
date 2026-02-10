package com.carManager.frontend.service;

import com.carManager.frontend.dto.CarDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CarRestService {

    private final RestClient restClient;

    public CarRestService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<CarDTO> getAllCars() {
        return restClient.get()
            .uri("/v1/cars")
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
    }

    public CarDTO getCar(long carId) {
        return restClient.get()
            .uri("/v1/cars/{carId}", carId)
            .retrieve()
            .body(CarDTO.class);
    }

    public void updateLicensePlate(long carId, String licensePlate) {
        restClient.put()
            .uri(uriBuilder -> uriBuilder
                .path("/v1/cars/{carId}")
                .queryParam("licensePlate", licensePlate)
                .build(carId))
            .retrieve()
            .toBodilessEntity();
    }
}
