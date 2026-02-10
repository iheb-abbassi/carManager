package com.carManager.controller.mapper;

import com.carManager.datatransferobject.CarDTO;
import com.carManager.domainobject.CarDO;
import com.carManager.domainvalue.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CarMapper Unit Tests")
class CarMapperTest {

    @Test
    @DisplayName("Should map all fields from CarDO to CarDTO")
    void makeCarDTO_MapsAllFields() {
        CarDO car = new CarDO("DE007", 4, true, 5, EngineType.ELECTRIC);
        car.setId(1L);

        CarDTO dto = CarMapper.makeCarDTO(car);

        assertEquals(1L, dto.id());
        assertEquals("DE007", dto.licensePlate());
        assertEquals(4, dto.seatCount());
        assertTrue(dto.convertible());
        assertEquals(5, dto.rating());
        assertEquals(EngineType.ELECTRIC, dto.engineType());
        assertNotNull(dto.dateCreated());
    }

    @Test
    @DisplayName("Should map all fields from CarDTO to CarDO")
    void makeCarDO_MapsAllFields() {
        CarDTO dto = new CarDTO(null, null, "DE007", 4, true, 5, EngineType.GAS);

        CarDO car = CarMapper.makeCarDO(dto);

        assertEquals("DE007", car.getLicensePlate());
        assertEquals(4, car.getSeatCount());
        assertTrue(car.isConvertible());
        assertEquals(5, car.getRating());
        assertEquals(EngineType.GAS, car.getEngineType());
    }

    @Test
    @DisplayName("Should map a collection of CarDO to a list of CarDTO")
    void makeCarDTOList_MapsCollection() {
        CarDO car1 = new CarDO("DE007", 4, true, 5, EngineType.ELECTRIC);
        car1.setId(1L);
        CarDO car2 = new CarDO("DE777", 2, false, 3, EngineType.GAS);
        car2.setId(2L);

        List<CarDTO> dtos = CarMapper.makeCarDTOList(List.of(car1, car2));

        assertEquals(2, dtos.size());
        assertEquals("DE007", dtos.get(0).licensePlate());
        assertEquals("DE777", dtos.get(1).licensePlate());
    }

    @Test
    @DisplayName("Should return empty list when mapping an empty collection")
    void makeCarDTOList_EmptyCollection() {
        List<CarDTO> dtos = CarMapper.makeCarDTOList(List.of());

        assertTrue(dtos.isEmpty());
    }
}
