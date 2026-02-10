package com.carManager;


import com.carManager.dataaccessobject.DriverRepository;
import com.carManager.domainobject.DriverDO;
import com.carManager.domainvalue.GeoCoordinate;
import com.carManager.domainvalue.OnlineStatus;
import com.carManager.exception.ConstraintsViolationException;
import com.carManager.exception.EntityNotFoundException;
import com.carManager.service.driver.DefaultDriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("DefaultDriverService Unit Tests")
class DefaultDriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DefaultDriverService driverService;

    private DriverDO driver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        driver = new DriverDO("testDriver", "password");
    }

    @Test
    @DisplayName("Should return driver when found by valid ID")
    void testFindDriver_Success() throws EntityNotFoundException {
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        DriverDO foundDriver = driverService.find(1L);

        assertNotNull(foundDriver);
        assertEquals("testDriver", foundDriver.getUsername());
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when driver ID does not exist")
    void testFindDriver_NotFound() {
        when(driverRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> driverService.find(1L));
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should create and return a new driver successfully")
    void testCreateDriver_Success() throws ConstraintsViolationException {
        when(driverRepository.save(driver)).thenReturn(driver);

        DriverDO createdDriver = driverService.create(driver);

        assertNotNull(createdDriver);
        assertEquals("testDriver", createdDriver.getUsername());
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    @DisplayName("Should throw ConstraintsViolationException when driver data violates constraints")
    void testCreateDriver_ConstraintViolation() {
        when(driverRepository.save(driver)).thenThrow(new DataIntegrityViolationException("Constraint violation"));

        assertThrows(ConstraintsViolationException.class, () -> driverService.create(driver));
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    @DisplayName("Should soft-delete driver by setting deleted flag to true")
    void testDeleteDriver_Success() throws EntityNotFoundException {
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        driverService.delete(1L);

        assertTrue(driver.getDeleted());
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should update driver coordinates with given longitude and latitude")
    void testUpdateLocation_Success() throws EntityNotFoundException {
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        driverService.updateLocation(1L, 20.0, 10.0);

        assertNotNull(driver.getCoordinate());
        assertEquals(20.0, driver.getCoordinate().getLongitude());
        assertEquals(10.0, driver.getCoordinate().getLatitude());
        verify(driverRepository, times(1)).findById(1L);
    }
}