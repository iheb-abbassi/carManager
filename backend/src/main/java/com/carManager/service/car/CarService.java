package com.carManager.service.car;

import com.carManager.domainobject.CarDO;
import com.carManager.exception.EntityNotFoundException;

import java.util.List;

public interface CarService
{
    CarDO find(Long carId) throws EntityNotFoundException;

    List<CarDO> findAll();

    void updateLicensePlate(long carId, String licensePlate) throws EntityNotFoundException;
}
