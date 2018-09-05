package com.mytaxi.domain;

import com.mytaxi.domain.shared.BaseEntity;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


    public boolean isOnline()
    {
        return this.onlineStatus == OnlineStatus.ONLINE;
    }

}
