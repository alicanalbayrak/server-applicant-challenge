package com.mytaxi.domain;

import javax.persistence.Embeddable;
import lombok.Value;

/**
 * Immutable License plate value object
 */
@Value
@Embeddable
public class LicensePlate
{


    private final String licensePlate;


    private LicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public static LicensePlate of(String licensePlate)
    {
        return new LicensePlate(licensePlate);
    }
}
