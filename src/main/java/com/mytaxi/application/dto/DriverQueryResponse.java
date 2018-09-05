package com.mytaxi.application.dto;

import com.mytaxi.domain.OnlineStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverQueryResponse
{

    private Long driverId;

    private String username;

    private OnlineStatus onlineStatus;


    private DriverQueryResponse()
    {
    }


    public DriverQueryResponse(Long driverId, String username, OnlineStatus onlineStatus)
    {
        this.driverId = driverId;
        this.username = username;
        this.onlineStatus = onlineStatus;
    }
}
