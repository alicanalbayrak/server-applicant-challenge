package com.mytaxi.domain;

public class CarService
{

    /**
     * Selects a Car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if car not found with given id
     */
    public Car find(Long carId) throws EntityNotFoundException
    {
        throw new UnsupportedOperationException();
    }


    /**
     * Creates a new car
     * @param car
     * @return Created Car object
     * @throws ConstraintsViolationException if a car already exists with the given license_plate, ... .
     */
    public Car create(Car car) throws ConstraintsViolationException
    {
        throw new UnsupportedOperationException();
    }


    /**
     * Deletes an existing car by id
     * @param carId
     */
    public void delete(Long carId) throws EntityNotFoundException
    {
        throw new UnsupportedOperationException();
    }


    /**
     * Updates Car's variant fields with given car object
     * @param carId
     * @param car
     * @throws EntityNotFoundException
     * @throws ConstraintsViolationException
     */
    public void update(Long carId, Car car) throws EntityNotFoundException, ConstraintsViolationException
    {
        throw new UnsupportedOperationException();
    }


}
