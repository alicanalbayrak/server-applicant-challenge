package com.mytaxi.application.service.impl;

import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.application.service.DriverService;
import com.mytaxi.domain.DriverDomainService;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;

/**
 * Check interface comments
 * @see com.mytaxi.application.service.DriverService
 */
public class DriverServiceImpl implements DriverService
{

    private DriverDomainService driverDomainService;

    @Override
    public DriverDTO find(Long driverId) throws EntityNotFoundException
    {
        return null;
    }


    @Override
    public DriverDTO create(DriverDTO newDriver) throws ConstraintsViolationException
    {
        return null;
    }


    @Override
    public void delete(Long driverId) throws EntityNotFoundException
    {

    }


    @Override
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {

    }


    @Override
    public List<DriverDTO> find(OnlineStatus onlineStatus)
    {
        return null;
    }
}
