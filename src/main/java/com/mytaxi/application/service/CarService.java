package com.mytaxi.application.service;

import com.mytaxi.application.dto.CarDTO;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;

/**
 * This interface is a application level service for Driver domain.
 * Responsible for communication abstraction between distributable services and domain service.
 */
public interface CarService
{
    CarDTO find(Long carId) throws EntityNotFoundException;

    CarDTO create(CarDTO newCar) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    CarDTO update(long carId, CarDTO carDTO) throws EntityNotFoundException, ConstraintsViolationException;

    List<CarDTO> listAllCars();

}
