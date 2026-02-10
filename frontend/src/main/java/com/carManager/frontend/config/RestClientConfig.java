package com.carManager.frontend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder, @Value("${backend.url}") String backendUrl) {
        return builder
            .baseUrl(backendUrl)
            .build();
    }
}
