package com.mytaxi.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domain.EngineType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverCarSelectDTO
{

    private Long driverId;

    private String username;

    private Long carId;

    private String licensePlate;

    private Integer seatCount;

    private Boolean convertible;

    private Double rating;

    private EngineType engineType;

    private String manufacturer;


    private DriverCarSelectDTO()
    {
    }


    public DriverCarSelectDTO(
        Long driverId,
        String username,
        Long carId,
        String licensePlate,
        Integer seatCount,
        Boolean convertible,
        Double rating,
        EngineType engineType, String manufacturer)
    {
        this.driverId = driverId;
        this.username = username;
        this.carId = carId;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }
}
