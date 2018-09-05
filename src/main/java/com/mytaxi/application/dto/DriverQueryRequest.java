package com.mytaxi.application.dto;

import com.mytaxi.domain.EngineType;
import com.mytaxi.domain.LicensePlate;
import com.mytaxi.domain.Manufacturer;
import com.mytaxi.domain.OnlineStatus;
import java.util.Map;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DriverQueryRequest
{

    private final Optional<String> username;

    private final Optional<OnlineStatus> onlineStatus;

    private final Optional<LicensePlate> licensePlate;

    private final Optional<Integer> seatCount;

    private final Optional<Boolean> convertible;

    private final Optional<EngineType> engineType;

    private final Optional<Manufacturer> manufacturer;


    // TODO refaftor!!!
    public static DriverQueryRequest parseInputParams(Map<String, String> params)
    {
        DriverQueryRequestBuilder builder = DriverQueryRequest.builder();

        // username
        builder.username(Optional.ofNullable(params.get("username")));

        //online status
        Optional<String> onlineStatusOpt = Optional.ofNullable(params.getOrDefault("onlineStatus", null));
        if (onlineStatusOpt.isPresent())
        {
            builder.onlineStatus(Optional.of(OnlineStatus.valueOf(onlineStatusOpt.get().toUpperCase())));
        }
        else
        {
            builder.onlineStatus(Optional.empty());
        }

        // license plate
        Optional<String> licensePlateOpt = Optional.ofNullable(params.getOrDefault("licensePlate", null));
        if (licensePlateOpt.isPresent())
        {
            builder.licensePlate(Optional.of(LicensePlate.of(licensePlateOpt.get())));
        }
        else
        {
            builder.licensePlate(Optional.empty());
        }

        // handle seat count
        Optional<String> seatCountOpt = Optional.ofNullable(params.getOrDefault("seatCount", null));
        if (seatCountOpt.isPresent())
        {
            builder.seatCount(Optional.of(Integer.valueOf(seatCountOpt.get())));
        }
        else
        {
            builder.seatCount(Optional.empty());
        }

        // handle convertible
        Optional<String> convertibleOpt = Optional.ofNullable(params.getOrDefault("convertible", null));

        if (convertibleOpt.isPresent())
        {
            builder.convertible(Optional.of(Boolean.valueOf(convertibleOpt.get())));
        }
        else
        {
            builder.convertible(Optional.empty());
        }

        // enginetype
        Optional<String> engineTypeOpt = Optional.ofNullable(params.getOrDefault("engineType", null));
        if (engineTypeOpt.isPresent())
        {
            builder.engineType(Optional.of(EngineType.valueOf(engineTypeOpt.get().toUpperCase())));
        }
        else
        {
            builder.engineType(Optional.empty());
        }

        Optional<String> manufacturerOpt = Optional.ofNullable(params.getOrDefault("manufacturer", null));
        if (manufacturerOpt.isPresent())
        {
            builder.manufacturer(Optional.of(Manufacturer.createManufacturer(manufacturerOpt.get())));
        }
        else
        {
            builder.manufacturer(Optional.empty());
        }

        return builder.build();
    }

}
