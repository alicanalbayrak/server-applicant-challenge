package com.mytaxi.domain;

import com.mytaxi.domain.shared.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DriverCarSelection",
    uniqueConstraints = {
        @UniqueConstraint(name = "uc_driver_id", columnNames = {"DriverId"}),
        @UniqueConstraint(name = "uc_car_id", columnNames = {"CarId"})
    })
public class DriverCarSelection extends BaseEntity<DriverCarSelection>
{
    @Column(name = "DriverId", nullable = false)
    @NotNull(message = "driver cannot be null")
    private Long driverId;

    @Column(name = "CarId", nullable = false)
    @NotNull(message = "car cannot be null")
    private Long carId;

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version = 0;


    private DriverCarSelection()
    {
    }


    public DriverCarSelection(
        @NotNull(message = "driver cannot be null") Long driverId,
        @NotNull(message = "car cannot be null") Long carId)
    {
        this.driverId = driverId;
        this.carId = carId;
        this.version = 0;
    }
}
