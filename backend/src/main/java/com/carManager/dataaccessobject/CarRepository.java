package com.carManager.dataaccessobject;

import com.carManager.domainobject.CarDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<CarDO, Long>
{
    List<CarDO> findByDeletedFalse();
}
