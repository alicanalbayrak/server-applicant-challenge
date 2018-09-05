package com.mytaxi.application.mapper;

import com.mytaxi.application.dto.DriverCarSelectDTO;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverWithSelectedCar;

public class DriverCarSelectionMapper
{

    public static DriverCarSelectDTO toDto(DriverWithSelectedCar driverCarSelection)
    {

        Driver driver = driverCarSelection.getDriver();
        Car car = driverCarSelection.getCar();

        return DriverCarSelectDTO.builder()
            .driverId(driver.getId())
            .username(driver.getUsername())
            .carId(car.getId())
            .convertible(car.getConvertible())
            .engineType(car.getEngineType())
            .licensePlate(car.getLicensePlate().getValue())
            .manufacturer(car.getManufacturer().getName())
            .rating(car.getRating().getRating())
            .seatCount(car.getSeatCount().getValue())
            .build();


    }

}
