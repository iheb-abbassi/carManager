package com.carManager.controller;

import com.carManager.controller.mapper.CarMapper;
import com.carManager.datatransferobject.CarDTO;
import com.carManager.exception.EntityNotFoundException;
import com.carManager.service.car.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cars")
public class CarController
{
    private final CarService carService;

    @Autowired
    public CarController(final CarService carService){this.carService = carService;}

    @GetMapping
    public List<CarDTO> getAllCars()
    {
        return CarMapper.makeCarDTOList(carService.findAll());
    }

    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable(name = "carId") long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }

    @PutMapping("/{carId}")
    public void updateCar(@PathVariable(name = "carId") long carId, @RequestParam(name = "licensePlate") String licensePlate) throws EntityNotFoundException
    {
        carService.updateLicensePlate(carId, licensePlate);
    }



}
