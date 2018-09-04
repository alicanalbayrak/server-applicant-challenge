package com.mytaxi.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverCarSelectDTO
{

    @NotNull
    private long driverId;

    @NotNull
    private long carId;

    private DriverCarSelectDTO(){}

    public DriverCarSelectDTO(@NotNull long driverId, @NotNull long carId)
    {
        this.driverId = driverId;
        this.carId = carId;
    }
}
