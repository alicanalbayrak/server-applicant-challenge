package com.mytaxi.domain;

import com.mytaxi.domain.shared.BaseEntity;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(
    name = "driver",
    uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"})
)
public class Driver extends BaseEntity<Driver>
{

    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Embedded
    private GeoCoordinate coordinate;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnlineStatus onlineStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SelectedCar")
    private Car selectedCar;


    protected Driver()
    {
    }


    public Driver(
        @NotNull(message = "Username can not be null!") String username,
        @NotNull(message = "Password can not be null!") String password)
    {
        this.username = username;
        this.password = password;
        this.coordinate = null;
        this.dateCoordinateUpdated = null;
        this.onlineStatus = OnlineStatus.OFFLINE;
    }


    public void setCoordinate(GeoCoordinate coordinate)
    {
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = ZonedDateTime.now();
    }


    public void selectCar(Car car)
    {
        if(onlineStatus != OnlineStatus.ONLINE){

        }

        this.selectedCar = car;
        car.setInUse(true);
    }


    public void deselectCar()
    {
        if (this.selectedCar != null)
        {
            this.selectedCar.setInUse(false);
        }
        this.selectedCar = null;
    }

}
