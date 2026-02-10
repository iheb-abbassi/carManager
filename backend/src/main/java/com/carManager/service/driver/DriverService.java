package com.carManager.service.driver;

import com.carManager.domainobject.DriverDO;
import com.carManager.domainvalue.OnlineStatus;
import com.carManager.exception.ConstraintsViolationException;
import com.carManager.exception.EntityNotFoundException;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

}
