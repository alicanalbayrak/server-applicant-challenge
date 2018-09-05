package com.mytaxi.application.service.impl;

import com.mytaxi.application.dto.DriverCarSelectDTO;
import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.application.mapper.DriverCarSelectionMapper;
import com.mytaxi.application.mapper.DriverMapper;
import com.mytaxi.application.service.DriverService;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverCarSelectionDomainService;
import com.mytaxi.domain.DriverDomainService;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.domain.shared.CarAlreadyInUseException;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.DriverAlreadySelectedCarException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import com.mytaxi.domain.shared.OfflineDriverCarSelectionException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is a application level service for Driver domain.
 * Responsible for communication abstraction between distributable rest interface and domain service.
 * Handles DTO - Entity conversion and delegates service calls to domain layer service
 */
@Service
public class DriverServiceImpl implements DriverService
{

    private DriverDomainService driverDomainService;

    private DriverCarSelectionDomainService driverCarSelectionDomainService;


    @Override
    public DriverDTO find(Long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverDomainService.find(driverId));
    }


    @Override
    public DriverDTO create(DriverDTO driverDTO) throws ConstraintsViolationException
    {
        Driver driver = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverDomainService.create(driver));
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
        return DriverMapper.makeDriverDTOList(driverDomainService.find(onlineStatus));
    }


    @Override
    public DriverCarSelectDTO selectCar(long driverId, long carId) throws DriverAlreadySelectedCarException, CarAlreadyInUseException, OfflineDriverCarSelectionException,
                                                                          EntityNotFoundException
    {
        return DriverCarSelectionMapper.toDto(driverCarSelectionDomainService.selectCar(driverId, carId));
    }


    @Override
    public void deselectCar(long driverId) throws EntityNotFoundException
    {
        driverCarSelectionDomainService.deselectCar(driverId);
    }


    @Override
    public List<DriverDTO> find(OnlineStatus onlineStatus, String username)
    {
        return DriverMapper.makeDriverDTOList(driverDomainService.findAllByOnlineStatusOrUsername(onlineStatus, username));
    }

    /**
     * Injection point
     *
     * @param driverDomainService
     */
    @Autowired
    public void setDriverDomainService(DriverDomainService driverDomainService)
    {
        this.driverDomainService = driverDomainService;
    }


    @Autowired
    public void setDriverCarSelectionDomainService(DriverCarSelectionDomainService driverCarSelectionDomainService)
    {
        this.driverCarSelectionDomainService = driverCarSelectionDomainService;
    }
}
