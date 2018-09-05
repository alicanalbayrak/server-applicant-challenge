package com.mytaxi.domain;

import java.util.Optional;

public interface DriverCarSelectionRepository
{

    Optional<DriverCarSelection> findEntryByDriverId(long driverId);

    Optional<DriverCarSelection> findEntryByCarId(long carId);

    DriverCarSelection save(DriverCarSelection driverCarSelection);

    void delete(Long id);
}
