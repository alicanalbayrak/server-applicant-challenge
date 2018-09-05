package com.mytaxi.domain.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Driver already selected a car!")
public class DriverAlreadySelectedCarException extends Exception
{
    public DriverAlreadySelectedCarException(String message)
    {
        super(message);
    }
}
