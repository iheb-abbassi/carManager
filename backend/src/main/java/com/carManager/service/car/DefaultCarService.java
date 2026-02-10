package com.carManager.service.car;

import com.carManager.dataaccessobject.CarRepository;
import com.carManager.domainobject.CarDO;
import com.carManager.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCarService implements CarService
{

    private final CarRepository carRepository;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find car with id: " + carId));
    }


    @Override
    public List<CarDO> findAll()
    {
        return carRepository.findByDeletedFalse();
    }


    @Override
    @Transactional
    public void updateLicensePlate(long carId, String licensePlate) throws EntityNotFoundException
    {
        CarDO car = find(carId);
        car.setLicensePlate(licensePlate);
    }
}
