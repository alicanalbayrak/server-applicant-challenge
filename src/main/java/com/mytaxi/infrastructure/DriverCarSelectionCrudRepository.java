package com.mytaxi.infrastructure;

import com.mytaxi.domain.DriverCarSelection;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface DriverCarSelectionCrudRepository extends CrudRepository<DriverCarSelection, Long>
{

    Optional<DriverCarSelection> findByDriverId(long driverId);

    Optional<DriverCarSelection> findByCarId(long carId);

}
