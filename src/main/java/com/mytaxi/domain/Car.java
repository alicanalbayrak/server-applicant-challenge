package com.mytaxi.domain;

import com.mytaxi.domain.shared.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends BaseEntity<Car>
{

    @Column(name = "LicensePlate")
    private LicensePlate licensePlate;

    @Column(name = "SeatCount")
    private SeatCount seatCount;

    @Column(name = "Convertible")
    private Boolean convertible;

    @Column(name = "Rating")
    private Rating rating;

    @Column(name = "EngineType")
    private EngineType engineType;

}
