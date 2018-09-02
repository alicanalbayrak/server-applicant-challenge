package com.mytaxi.infrastructure;

import com.mytaxi.domain.Car;
import org.springframework.data.repository.CrudRepository;

public interface CrudCarRepository extends CrudRepository<Car, Long>
{

}
