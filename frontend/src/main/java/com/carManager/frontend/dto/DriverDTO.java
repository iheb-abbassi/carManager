package com.carManager.frontend.dto;

public record DriverDTO(Long id, String username, String password, GeoCoordinate coordinate) {
}
