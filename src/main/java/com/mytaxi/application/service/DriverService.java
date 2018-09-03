package com.mytaxi.application.service;

import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;

/**
 *  This interface is a application level service for Driver domain.
 *  Responsible for communication abstraction between distributable services and domain service.
 */
public interface DriverService
{
    DriverDTO find(Long driverId) throws EntityNotFoundException;

    DriverDTO create(DriverDTO newDriver) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDTO> find(OnlineStatus onlineStatus);
}
