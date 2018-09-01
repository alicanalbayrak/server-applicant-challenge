package com.mytaxi.domain;

import java.util.List;
import java.util.Optional;

public interface DriverRepository
{
    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);

    Optional<Driver> findById(Long driverId);

    Driver save(Driver driver);

}
