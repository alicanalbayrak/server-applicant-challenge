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
    @Override
    public Optional<Car> findById(Long carId)
    {
        return Optional.empty();
    }


    @Override
    public Car save(Car car)
    {
        return null;
    }
}
