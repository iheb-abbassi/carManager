package com.carManager.controller;

import com.carManager.domainobject.CarDO;
import com.carManager.domainvalue.EngineType;
import com.carManager.service.car.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@DisplayName("CarController Integration Tests")
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    @DisplayName("GET /v1/cars should return a list of all cars")
    void getAllCars_ReturnsList() throws Exception {
        CarDO car = new CarDO("DE007", 4, true, 5, EngineType.ELECTRIC);
        car.setId(1L);
        when(carService.findAll()).thenReturn(List.of(car));

        mockMvc.perform(get("/v1/cars"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].licensePlate", is("DE007")))
            .andExpect(jsonPath("$[0].seatCount", is(4)));
    }

    @Test
    @DisplayName("GET /v1/cars/{id} should return a single car by ID")
    void getCar_ReturnsCarById() throws Exception {
        CarDO car = new CarDO("DE007", 4, true, 5, EngineType.ELECTRIC);
        car.setId(1L);
        when(carService.find(1L)).thenReturn(car);

        mockMvc.perform(get("/v1/cars/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.licensePlate", is("DE007")))
            .andExpect(jsonPath("$.engineType", is("ELECTRIC")));
    }

    @Test
    @DisplayName("PUT /v1/cars/{id} should update the license plate via service")
    void updateCar_CallsService() throws Exception {
        mockMvc.perform(put("/v1/cars/1").param("licensePlate", "NEW123"))
            .andExpect(status().isOk());

        verify(carService).updateLicensePlate(1L, "NEW123");
    }
}
