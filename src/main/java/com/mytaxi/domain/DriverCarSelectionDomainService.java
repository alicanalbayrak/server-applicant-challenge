package com.mytaxi.domain;

import com.mytaxi.domain.shared.CarAlreadyInUseException;
import com.mytaxi.domain.shared.DriverAlreadySelectedCarException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import com.mytaxi.domain.shared.OfflineDriverCarSelectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 * This is a domain layer service to provide selecting/deselecting car by driver.
 * Communicates with other domain roots to handle selection operation.
 */
@Component
public class DriverCarSelectionDomainService
{

    private static final Logger LOG = LoggerFactory.getLogger(DriverCarSelectionDomainService.class);

    private CarDomainService carDomainService;

    private DriverDomainService driverDomainService;

    private DriverCarSelectionRepository driverCarSelectionRepository;


    /**
     * Retrive and ask for Car availability by carId. If Car is available hold for the driver.
     *
     * @param driverId
     * @param carId
     * @return Driver that selects car
     * @throws EntityNotFoundException  Driver or Car not found
     * @throws CarAlreadyInUseException Car is in use.
     */
    public DriverWithSelectedCar selectCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException, OfflineDriverCarSelectionException,
                                                                             DriverAlreadySelectedCarException
    {
        Driver driver = driverDomainService.find(driverId);
        if (!driver.isOnline())
        {
            throw new OfflineDriverCarSelectionException("Offline driver cannot select a car!");
        }

        // TODO defensive coding here may removed
        checkDriverAlreadySelectACar(driverId);

        // TODO defensive coding here may removed
        Car car = carDomainService.find(carId);
        checkCarAlreadyInUse(carId);

        DriverCarSelection driverCarSelection;
        try
        {
            driverCarSelection = driverCarSelectionRepository.save(new DriverCarSelection(driver.getId(), car.getId()));
        }
        catch (DataIntegrityViolationException e)
        {
            throw new CarAlreadyInUseException("Car already selected");
        }

        return new DriverWithSelectedCar(driver, car);

    }


    /**
     * Deselect if any Car in use.
     *
     * @param driverId
     * @throws EntityNotFoundException Driver not found
     */
    public void deselectCar(long driverId) throws EntityNotFoundException
    {

        DriverCarSelection driverCarSelection = driverCarSelectionRepository.findEntryByDriverId(driverId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Could not find any driver with id: %s that selected a car!", driverId)));

        driverCarSelectionRepository.delete(driverCarSelection.getId());

    }


    /**
     * Prevents to car to be selected, if already is being used by another driver
     *
     * @param carId
     * @throws CarAlreadyInUseException
     */
    private void checkCarAlreadyInUse(long carId) throws CarAlreadyInUseException
    {
        if (driverCarSelectionRepository.findEntryByCarId(carId).isPresent())
        {
            LOG.error(String.format("Car with id: %s already selected", carId));
            throw new CarAlreadyInUseException("Car already in use...");
        }
    }


    /**
     * Prevents driver to select a new car, if already a car assigned to it.
     *
     * @param driverId
     * @throws DriverAlreadySelectedCarException
     */
    private void checkDriverAlreadySelectACar(long driverId) throws DriverAlreadySelectedCarException
    {
        if (driverCarSelectionRepository.findEntryByDriverId(driverId).isPresent())
        {
            LOG.error(String.format("Driver with id: %s already selected a car", driverId));
            throw new DriverAlreadySelectedCarException("Driver already selected a car...");
        }
    }


    @Autowired
    public void setCarDomainService(CarDomainService carDomainService)
    {
        this.carDomainService = carDomainService;
    }


    @Autowired
    public void setDriverDomainService(DriverDomainService driverDomainService)
    {
        this.driverDomainService = driverDomainService;
    }


    @Autowired
    public void setDriverCarSelectionRepository(DriverCarSelectionRepository driverCarSelectionRepository)
    {
        this.driverCarSelectionRepository = driverCarSelectionRepository;
    }
}
