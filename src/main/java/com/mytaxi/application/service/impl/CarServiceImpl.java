package com.mytaxi.application.service.impl;

import com.mytaxi.application.dto.CarDTO;
import com.mytaxi.application.mapper.CarMapper;
import com.mytaxi.application.service.CarService;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.CarDomainService;
import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Check interface comments
 *
 * @see com.mytaxi.application.service.CarService
 */
@Service
public class CarServiceImpl implements CarService
{
    private final CarDomainService carDomainService;


    @Autowired
    public CarServiceImpl(CarDomainService carDomainService)
    {
        this.carDomainService = carDomainService;
    }


    @Override
    public CarDTO find(Long carId) throws EntityNotFoundException
    {

        return CarMapper.toDto(carDomainService.find(carId));
    }


    @Override
    public CarDTO create(CarDTO newCar) throws ConstraintsViolationException, EntityNotFoundException
    {
        Car car = CarMapper.toEntity(newCar);
        return CarMapper.toDto(carDomainService.create(car));
    }


    @Override
    public void delete(Long carId) throws EntityNotFoundException
    {
        carDomainService.delete(carId);
    }


    @Override
    public CarDTO update(long carId, CarDTO carDTO) throws EntityNotFoundException, ConstraintsViolationException
    {
        Car car = CarMapper.toEntity(carDTO);
        return CarMapper.toDto(carDomainService.update(carId, car));
    }


    @Override
    public List<CarDTO> listAllCars()
    {
        return CarMapper.toDtoList(carDomainService.listCars());
    }



}