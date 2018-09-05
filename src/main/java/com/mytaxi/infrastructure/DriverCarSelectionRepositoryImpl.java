package com.mytaxi.infrastructure;

import com.mytaxi.domain.DriverCarSelection;
import com.mytaxi.domain.DriverCarSelectionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverCarSelectionRepositoryImpl implements DriverCarSelectionRepository
{

    private DriverCarSelectionCrudRepository driverCarSelectionCrudRepository;


    @Override
    public Optional<DriverCarSelection> findEntryByDriverId(long driverId)
    {
        return driverCarSelectionCrudRepository.findByDriverId(driverId);
    }


    @Override
    public Optional<DriverCarSelection> findEntryByCarId(long carId)
    {
        return driverCarSelectionCrudRepository.findByCarId(carId);
    }


    @Override
    public DriverCarSelection save(DriverCarSelection driverCarSelection)
    {
        return driverCarSelectionCrudRepository.save(driverCarSelection);
    }


    @Override
    public void delete(Long id)
    {
        driverCarSelectionCrudRepository.deleteById(id);
    }


    @Autowired
    public void setDriverCarSelectionCrudRepository(DriverCarSelectionCrudRepository driverCarSelectionCrudRepository)
    {
        this.driverCarSelectionCrudRepository = driverCarSelectionCrudRepository;
    }
}
