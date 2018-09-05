package com.mytaxi.application.mapper;

import com.mytaxi.application.dto.DriverQueryResponse;
import com.mytaxi.domain.Driver;
import java.util.List;
import java.util.stream.Collectors;

public class DriverQueryMapper
{

    public static DriverQueryResponse toDto(Driver driver)
    {
        return new DriverQueryResponse(driver.getId(), driver.getUsername(), driver.getOnlineStatus());
    }


    public static List<DriverQueryResponse> toDtoList(List<Driver> driverList)
    {
        return driverList.stream()
            .map(driver -> new DriverQueryResponse(driver.getId(), driver.getUsername(), driver.getOnlineStatus()))
            .collect(Collectors.toList());
    }

}
