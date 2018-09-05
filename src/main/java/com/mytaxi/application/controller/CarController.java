package com.mytaxi.application.controller;

import com.mytaxi.application.dto.CarDTO;
import com.mytaxi.application.service.CarService;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/car")
public class CarController
{

    private CarService carService;


    @GetMapping("/list")
    public List<CarDTO> listCars()
    {
        return carService.listAllCars();
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException
    {
        return carService.find(carId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException, EntityNotFoundException
    {
        return carService.create(carDTO);
    }


    @Transactional
    @PutMapping("/{carId}")
    public CarDTO updateCar(@PathVariable long carId, @Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException, EntityNotFoundException
    {
        return carService.update(carId, carDTO);
    }

    @Transactional
    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }


    @Autowired
    public void setCarService(CarService carService)
    {
        this.carService = carService;
    }
}
