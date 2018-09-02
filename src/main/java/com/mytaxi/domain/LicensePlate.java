package com.mytaxi.domain;

import javax.persistence.Column;
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

}
