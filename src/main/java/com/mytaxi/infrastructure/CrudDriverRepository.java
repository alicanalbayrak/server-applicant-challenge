package com.mytaxi.infrastructure;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.OnlineStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface CrudDriverRepository extends CrudRepository<Driver, Long>
{

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);
}
