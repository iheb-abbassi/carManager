package com.carManager.frontend.dto;

import com.carManager.frontend.enums.EngineType;
import java.time.ZonedDateTime;

public record CarDTO(
    Long id,
    ZonedDateTime dateCreated,
    String licensePlate,
    Integer seatCount,
    Boolean convertible,
    Integer rating,
    EngineType engineType
) {
}
