package com.mytaxi.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Value;

/**
 * Immutable seat count value object
 */
@Value
@Embeddable
public class SeatCount
{


    private Integer seatCount;

}
