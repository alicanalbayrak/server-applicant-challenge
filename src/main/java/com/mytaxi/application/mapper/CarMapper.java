package com.mytaxi.application.mapper;

import com.mytaxi.application.dto.CarDTO;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.LicensePlate;
import com.mytaxi.domain.Manufacturer;
import com.mytaxi.domain.Rating;
import com.mytaxi.domain.SeatCount;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{

    public static Car toEntity(CarDTO carDTO)
    {
        return Car.builder()
            .licensePlate(LicensePlate.of(carDTO.getLicensePlate()))
            .seatCount(SeatCount.of(carDTO.getSeatCount()))
            .convertible(carDTO.getConvertible())
            .rating(Rating.of(carDTO.getRating()))
            .engineType(carDTO.getEngineType())
            .manufacturer(Manufacturer.createManufacturer(carDTO.getManufacturer()))
            .build();
    }


    public static CarDTO toDto(Car car)
    {
        return CarDTO.builder()
            .id(car.getId())
            .licensePlate(car.getLicensePlate().getValue())
            .seatCount(car.getSeatCount().getValue())
            .convertible(car.getConvertible())
            .rating(car.getRating().getValue())
            .engineType(car.getEngineType())
            .manufacturer(car.getManufacturer().getName())
            .build();
    }


    public static List<CarDTO> toDtoList(List<Car> carList)
    {
        if (carList == null || carList.isEmpty())
        {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(carList.stream().map(CarMapper::toDto).collect(Collectors.toList()));
    }

}
