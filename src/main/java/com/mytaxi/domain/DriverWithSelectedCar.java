package com.mytaxi.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DriverWithSelectedCar
{

    private final Driver driver;

    private final Car car;

}
