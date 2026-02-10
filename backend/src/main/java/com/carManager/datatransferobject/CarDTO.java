package com.carManager.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.carManager.domainvalue.EngineType;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CarDTO
    (
        Long id,

        ZonedDateTime dateCreated,

        @NotNull
        String licensePlate,

        @NotNull
        Integer seatCount,

        @NotNull
        Boolean convertible,

        @NotNull
        Integer rating,
        
        EngineType engineType
    )
{
}