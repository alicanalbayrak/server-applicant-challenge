package com.mytaxi.domain;

import java.util.Optional;

public interface CarRepository
{

    Optional<Car> findById(Long carId);

    Car save(Car car);

}
