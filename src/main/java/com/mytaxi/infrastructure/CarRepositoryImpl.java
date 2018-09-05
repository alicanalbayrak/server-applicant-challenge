package com.mytaxi.infrastructure;

import com.mytaxi.domain.Car;
import com.mytaxi.domain.CarRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarRepositoryImpl implements CarRepository
{

    private CarCrudRepository carCrudRepository;


    @Override
    public Optional<Car> findById(Long carId)
    {
        return carCrudRepository.findById(carId);
    }


    @Override
    public Car save(Car car)
    {
        return carCrudRepository.save(car);
    }


    @Override
    public Iterable<Car> findAll()
    {
        return carCrudRepository.findAll();
    }


    @Autowired
    public void setCarCrudRepository(CarCrudRepository carCrudRepository)
    {
        this.carCrudRepository = carCrudRepository;
    }
}
