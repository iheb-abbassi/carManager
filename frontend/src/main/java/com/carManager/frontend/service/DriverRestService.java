package com.carManager.frontend.service;

import com.carManager.frontend.dto.DriverDTO;
import com.carManager.frontend.enums.OnlineStatus;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DriverRestService {

    private static final Logger log = LoggerFactory.getLogger(DriverRestService.class);
    private final RestClient restClient;

    public DriverRestService(RestClient restClient) {
        this.restClient = restClient;
    }

    public DriverDTO getDriver(long driverId) {
        return restClient.get()
            .uri("/v1/drivers/{driverId}", driverId)
            .retrieve()
            .body(DriverDTO.class);
    }

    public List<DriverDTO> findByStatus(OnlineStatus status) {
        log.info("Finding drivers by status: {}", status);
        List<DriverDTO> result = restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/v1/drivers")
                .queryParam("onlineStatus", status.name())
                .build())
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
        log.info("Found {} drivers", result != null ? result.size() : 0);
        return result;
    }

    public void createDriver(String username, String password) {
        log.info("Creating driver: {}", username);
        restClient.post()
            .uri("/v1/drivers")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Map.of("username", username, "password", password))
            .retrieve()
            .toBodilessEntity();
        log.info("Driver created successfully");
    }

    public void deleteDriver(long driverId) {
        log.info("Deleting driver: {}", driverId);
        restClient.delete()
            .uri("/v1/drivers/{driverId}", driverId)
            .retrieve()
            .toBodilessEntity();
        log.info("Driver deleted successfully");
    }

    public void updateLocation(long driverId, double longitude, double latitude) {
        log.info("Updating location for driver {}: lon={}, lat={}", driverId, longitude, latitude);
        restClient.put()
            .uri(uriBuilder -> uriBuilder
                .path("/v1/drivers/{driverId}")
                .queryParam("longitude", longitude)
                .queryParam("latitude", latitude)
                .build(driverId))
            .retrieve()
            .toBodilessEntity();
        log.info("Location updated successfully");
    }
}
