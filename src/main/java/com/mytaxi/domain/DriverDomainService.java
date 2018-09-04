package com.mytaxi.domain;

import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Domain Service to encapsulate Driver specific domain things such entities, value objects.
 * May communicate with some other Aggregates or sub-entities.
 * <p/>
 */
@Service
public class DriverDomainService
{

    private static final Logger LOG = LoggerFactory.getLogger(DriverDomainService.class);

    private final DriverRepository driverRepository;

    public DriverDomainService(final DriverRepository driverRepository)
    {
        this.driverRepository = driverRepository;
    }

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
     * @param newDriver
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    public Driver create(Driver newDriver) throws ConstraintsViolationException
    {
        Driver driver;
        try
        {
            driver = driverRepository.save(newDriver);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", newDriver, e);
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

}