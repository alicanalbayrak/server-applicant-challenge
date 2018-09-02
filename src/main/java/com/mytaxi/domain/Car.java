package com.mytaxi.domain;

import com.mytaxi.domain.shared.BaseEntity;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_license_plate", columnNames = {"LicensePlate"})
)
public class Car extends BaseEntity<Car>
{

    @Embedded
    @Column(name = "LicensePlate", nullable = false, unique = true)
    private LicensePlate licensePlate;

    @Embedded
    @Column(name = "SeatCount", nullable = false)
    private SeatCount seatCount;

    @Column(name = "Convertible", nullable = false)
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


    protected Car()
    {
    }


    @Builder
    private Car(
        Long id,
        ZonedDateTime dateCreated,
        Boolean deleted,
        LicensePlate licensePlate,
        SeatCount seatCount,
        Boolean convertible,
        Rating rating,
        EngineType engineType, Manufacturer manufacturer)
    {
        super(id, dateCreated, deleted);
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }
}
