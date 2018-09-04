package com.mytaxi.domain;

import com.google.common.collect.Lists;
import com.mytaxi.domain.shared.CarAlreadyInUseException;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Domain Service to encapsulate Car specific domain things such entities, value objects.
 * May communicate with some other Aggregates
 * <p/>
 */
@Service
public class CarDomainService
{

    private static final Logger LOG = LoggerFactory.getLogger(CarDomainService.class);

    private final CarRepository carRepository;

    private final ManufacturerRepository manufacturerRepository;


    @Autowired
    public CarDomainService(final CarRepository carRepository, ManufacturerRepository manufacturerRepository)
    {
        this.carRepository = carRepository;
        this.manufacturerRepository = manufacturerRepository;
    }


    /**
     * Selects a Car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if car not found with given id
     */
    public Car find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Creates a new car
     *
     * @param newCar
     * @return Created Car object
     * @throws ConstraintsViolationException if a car already exists with the given license_plate, ... .
     */
    public Car create(Car newCar) throws ConstraintsViolationException, EntityNotFoundException
    {

        Manufacturer manufacturer = findManufacturerChecked(newCar.getManufacturer().getName());
        newCar.setManufacturer(manufacturer);

        Car car;
        try
        {
            car = carRepository.save(newCar);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a new car: {}", newCar, e);
            throw new ConstraintsViolationException(e.getMessage());
        }

        return car;
    }


    /**
     * Deletes an existing car by id
     *
     * @param carId
     */
    public void delete(Long carId) throws EntityNotFoundException
    {
        Car car = findCarChecked(carId);
        car.setDeleted(true);
    }


    /**
     * Updates Car's variant fields with given car object
     *
     * @param carId
     * @param car
     * @throws EntityNotFoundException
     */
    public Car update(long carId, Car car) throws EntityNotFoundException
    {

        Manufacturer manufacturer = findManufacturerChecked(car.getManufacturer().getName());

        Car existingCar = findCarChecked(carId);

        existingCar.setManufacturer(manufacturer);
        existingCar.setEngineType(car.getEngineType());
        existingCar.setConvertible(car.getConvertible());
        existingCar.setLicensePlate(car.getLicensePlate());
        existingCar.setRating(car.getRating());
        existingCar.setSeatCount(car.getSeatCount());

        return existingCar;

    }


    public Car getCarByIdIfAvailable(long carId) throws EntityNotFoundException, CarAlreadyInUseException
    {
        Car car = findCarChecked(carId);
        car.verifyNotInUse();
        return car;
    }


    /**
     * Retrieves car from datasource by its id
     *
     * @param carId
     * @return Car Entity
     * @throws EntityNotFoundException
     */
    private Car findCarChecked(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find car entity with id: " + carId));
    }


    /**
     * Retrieves manufacturer from datasource by name
     *
     * @param name
     * @return Manufacturer Entity
     * @throws EntityNotFoundException
     */
    private Manufacturer findManufacturerChecked(String name) throws EntityNotFoundException
    {
        return manufacturerRepository.findByName(name)
            .orElseThrow(() -> new EntityNotFoundException("Could not find manufacturer entity with name: " + name));
    }


    public List<Car> listCars()
    {
        return Lists.newArrayList(carRepository.findAll());
    }
}
