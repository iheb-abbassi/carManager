package com.carManager.dataaccessobject;

import com.carManager.domainobject.DriverDO;
import com.carManager.domainvalue.OnlineStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatusAndDeletedFalse(OnlineStatus onlineStatus);
}
