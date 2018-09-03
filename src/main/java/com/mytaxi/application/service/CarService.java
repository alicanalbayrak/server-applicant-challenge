package com.mytaxi.application.service;

import com.mytaxi.domain.Car;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;

/**
 *  This interface is a application level service for Driver domain.
 *  Responsible for communication abstraction between distributable services and domain service.
 */
public interface CarService
{
    Car find(Long carId) throws EntityNotFoundException;

    Car create(Car newCar) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void update(Car car) throws EntityNotFoundException, ConstraintsViolationException;

    List<Car> list();
}
