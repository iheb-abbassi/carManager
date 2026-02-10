package com.carManager.controller.mapper;

import com.carManager.datatransferobject.CarDTO;
import com.carManager.domainobject.CarDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{

    public static CarDO makeCarDO(CarDTO carDTO) {
        return new CarDO(carDTO.licensePlate(), carDTO.seatCount(), carDTO.convertible(), carDTO.rating(), carDTO.engineType());
    }

    public static CarDTO makeCarDTO(CarDO car) {
        return new CarDTO(
            car.getId(), car.getDateCreated(), car.getLicensePlate(), car.getSeatCount(), car.isConvertible(), car.getRating(), car.getEngineType()
        );
    }

    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) {
        return cars.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
