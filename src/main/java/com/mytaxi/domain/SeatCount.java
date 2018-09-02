package com.mytaxi.domain;

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


    private SeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public static SeatCount of(Integer seatCount)
    {
        return new SeatCount(seatCount);
    }
}
