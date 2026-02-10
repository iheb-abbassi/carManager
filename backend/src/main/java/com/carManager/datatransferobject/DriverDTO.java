package com.carManager.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.carManager.domainvalue.GeoCoordinate;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DriverDTO
    (
        @JsonProperty
        Long id,
        @NotNull(message = "Username can not be null!")
        String username,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotNull(message = "Password can not be null!")
        String password,

        GeoCoordinate coordinate
    )
{
}
