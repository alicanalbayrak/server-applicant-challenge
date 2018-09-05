package com.mytaxi.infrastructure;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.OnlineStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverCrudRepository extends CrudRepository<Driver, Long>
{

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);

    @Query("select d from Driver d where (?1 is null or d.onlineStatus = ?1) and (?2 is null or d.username = ?2)")
    List<Driver> findByOnlineStatusOrUsername(OnlineStatus onlineStatus, String username);
}
