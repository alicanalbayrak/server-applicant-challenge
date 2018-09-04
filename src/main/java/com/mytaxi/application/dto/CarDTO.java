package com.mytaxi.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domain.EngineType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{

    @JsonIgnore
    private Long id;

    private String licensePlate;

    private Integer seatCount;

    private Boolean convertible;

    private Double rating;

    private EngineType engineType;

    private String manufacturer;


    public CarDTO()
    {

    }


    private CarDTO(Long id, String licensePlate, Integer seatCount, Boolean convertible, Double rating, EngineType engineType, String manufacturer)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


}
