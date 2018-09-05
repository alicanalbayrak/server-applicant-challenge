package com.mytaxi.domain;

import java.util.List;
import java.util.Optional;

/**
 * We do not want to use high level infrastructure details here.
 * So this is repository contract for infrastructure layer implementor.
 */
public interface DriverRepository
{

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);

    Optional<Driver> findById(Long driverId);

    Driver save(Driver driver);

    Iterable<Driver> findAllByOnlineStatusOrUsername(OnlineStatus onlineStatus, String username);
}
