package com.mytaxi.application.mapper;

import com.mytaxi.application.dto.DriverCarSelectDTO;
import com.mytaxi.domain.Driver;

public class DriverCarSelectMapper
{
    public static DriverCarSelectDTO from(Driver driver){
        return new DriverCarSelectDTO(driver.getId(), driver.getSelectedCar().getId());
    }

}
