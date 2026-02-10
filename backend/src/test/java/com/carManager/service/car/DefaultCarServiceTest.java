package com.carManager.service.car;

import com.carManager.dataaccessobject.CarRepository;
import com.carManager.domainobject.CarDO;
import com.carManager.domainvalue.EngineType;
import com.carManager.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("DefaultCarService Unit Tests")
class DefaultCarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private DefaultCarService carService;

    private CarDO car;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        car = new CarDO("DE007", 4, true, 5, EngineType.ELECTRIC);
        car.setId(1L);
    }

    @Test
    @DisplayName("Should return car when found by valid ID")
    void testFind_Success() throws EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        CarDO found = carService.find(1L);

        assertNotNull(found);
        assertEquals("DE007", found.getLicensePlate());
        assertEquals(4, found.getSeatCount());
        verify(carRepository).findById(1L);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when car ID does not exist")
    void testFind_NotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> carService.find(1L));
        verify(carRepository).findById(1L);
    }

    @Test
    @DisplayName("Should return only non-deleted cars")
    void testFindAll_ReturnsNonDeletedCars() {
        CarDO car2 = new CarDO("DE777", 2, false, 3, EngineType.GAS);
        when(carRepository.findByDeletedFalse()).thenReturn(List.of(car, car2));

        List<CarDO> cars = carService.findAll();

        assertEquals(2, cars.size());
        verify(carRepository).findByDeletedFalse();
    }

    @Test
    @DisplayName("Should return empty list when no cars exist")
    void testFindAll_Empty() {
        when(carRepository.findByDeletedFalse()).thenReturn(List.of());

        List<CarDO> cars = carService.findAll();

        assertTrue(cars.isEmpty());
    }

    @Test
    @DisplayName("Should update license plate for an existing car")
    void testUpdateLicensePlate_Success() throws EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        carService.updateLicensePlate(1L, "NEW123");

        assertEquals("NEW123", car.getLicensePlate());
        verify(carRepository).findById(1L);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when updating license plate for non-existent car")
    void testUpdateLicensePlate_NotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> carService.updateLicensePlate(1L, "NEW123"));
    }
}
