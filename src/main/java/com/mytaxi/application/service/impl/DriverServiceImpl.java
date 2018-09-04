package com.mytaxi.application.service.impl;

import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.application.mapper.DriverMapper;
import com.mytaxi.application.service.DriverService;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverDomainService;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Check interface comments
 * @see com.mytaxi.application.service.DriverService
 */
@Service
public class DriverServiceImpl implements DriverService
{

    private DriverDomainService driverDomainService;

    @Override
    public DriverDTO find(Long driverId) throws EntityNotFoundException
    {
        return DriverMapper.toDto(driverDomainService.find(driverId));
    }


    @Override
    public DriverDTO create(DriverDTO newDriver) throws ConstraintsViolationException
    {
        Driver driver = DriverMapper.toEntity(newDriver);
        return DriverMapper.toDto(driverDomainService.create(driver));
    }


    @Override
    public void delete(Long driverId) throws EntityNotFoundException
    {
        driverDomainService.delete(driverId);
    }


    @Override
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        driverDomainService.updateLocation(driverId, longitude, latitude);
    }


    @Override
    public List<DriverDTO> find(OnlineStatus onlineStatus)
    {
        return DriverMapper.toDtoList(driverDomainService.find(onlineStatus));
    }
}
