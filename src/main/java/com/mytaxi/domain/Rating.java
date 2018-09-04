package com.mytaxi.domain;

import javax.persistence.Embeddable;
import lombok.Value;

/**
 * Immutable rating value object
 */
@Value
@Embeddable
public class Rating
{

    private Double rating;


    protected Rating()
    {
        rating = null;
    }


    private Rating(Double rating)
    {
        this.rating = rating;
    }


    public static Rating of(Double rating)
    {
        return new Rating(rating);
    }


    public double getValue()
    {
        return rating;
    }
}
