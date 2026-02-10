package com.carManager.controller.mapper;

import com.carManager.datatransferobject.DriverDTO;
import com.carManager.domainobject.DriverDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMapper
{
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.username(), driverDTO.password());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        return new DriverDTO(driverDO.getId(), driverDO.getUsername(), driverDO.getPassword(), driverDO.getCoordinate());
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
