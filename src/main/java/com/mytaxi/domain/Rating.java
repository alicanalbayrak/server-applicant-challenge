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

}
