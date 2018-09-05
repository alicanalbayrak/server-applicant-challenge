package com.mytaxi.application.mapper;

import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.GeoCoordinate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMapper
{
    public static Driver makeDriverDO(DriverDTO driverDTO)
    {
        return new Driver(driverDTO.getUsername(), driverDTO.getPassword());
    }


    public static DriverDTO makeDriverDTO(Driver driver)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driver.getId())
            .setPassword(driver.getPassword())
            .setUsername(driver.getUsername());

        GeoCoordinate coordinate = driver.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<Driver> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
