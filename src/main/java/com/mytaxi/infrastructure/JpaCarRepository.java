package com.mytaxi.infrastructure;

import com.mytaxi.domain.Car;
import com.mytaxi.domain.CarRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * A repository for Car entities implemented with Spring Data Jpa
 */
@Component
public class JpaCarRepository implements CarRepository
{

    private final CrudCarRepository crudCarRepository;


    public JpaCarRepository(CrudCarRepository crudCarRepository)
    {
        this.crudCarRepository = crudCarRepository;
    }


    @Override
    public Optional<Car> findById(Long carId)
    {
        return crudCarRepository.findById(carId);
    }


    @Override
    public Car save(Car car)
    {
        return crudCarRepository.save(car);
    }


    @Override
    public Iterable<Car> findAll()
    {
        return crudCarRepository.findAll();
    }
}
