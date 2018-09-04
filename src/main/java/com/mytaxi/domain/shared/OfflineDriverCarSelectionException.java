package com.mytaxi.domain.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Offline driver cannot select a car!")
public class OfflineDriverCarSelectionException extends Exception
{

    public OfflineDriverCarSelectionException(String message)
    {
        super(message);
    }
}
