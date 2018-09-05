package com.mytaxi.domain;

import com.google.common.collect.Lists;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and app layer sevice and to have business logic for some driver specific things.
 * <p/>
 */
@Component
public class DriverDomainService
{

    private static final Logger LOG = LoggerFactory.getLogger(DriverDomainService.class);

    private DriverRepository driverRepository;


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    public Driver find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    public Driver create(Driver driverDO) throws ConstraintsViolationException
    {
        Driver driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        Driver driver = findDriverChecked(driverId);
        driver.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        Driver driver = findDriverChecked(driverId);
        driver.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    public List<Driver> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private Driver findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }


    @Autowired
    public void setDriverRepository(DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }


    public List<Driver> findAllByOnlineStatusOrUsername(OnlineStatus onlineStatus, String username)
    {
        return Lists.newArrayList(driverRepository.findAllByOnlineStatusOrUsername(onlineStatus, username));
    }
}
