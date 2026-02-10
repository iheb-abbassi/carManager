package com.carManager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{
    @Bean
    public OpenAPI openAPI()
    {
        return new OpenAPI()
            .info(new Info().title("CarManager API")
                .description("CarManager fleet management API with driver & car resources.")
                .version("1.0"));
    }
}