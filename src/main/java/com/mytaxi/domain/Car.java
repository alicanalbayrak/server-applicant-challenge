package com.mytaxi.domain;

import com.mytaxi.domain.shared.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends BaseEntity<Car>
{

    @Embedded
    @Column(name = "LicensePlate")
    private LicensePlate licensePlate;

    @Embedded
    @Column(name = "SeatCount")
    private SeatCount seatCount;

    @Column(name = "Convertible")
    private Boolean convertible;

    @Embedded
    @Column(name = "Rating")
    private Rating rating;


    @Enumerated(EnumType.STRING)
    @Column(name = "EngineType")
    private EngineType engineType;

    @ManyToOne
    @JoinColumn(name = "ManufacturerId")
    private Manufacturer manufacturer;

}
