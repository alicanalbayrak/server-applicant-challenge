package com.mytaxi.infrastructure;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverRepository;
import com.mytaxi.domain.OnlineStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * This component is used to delegates repository calls to the Spring CrudRepository.
 * Main idea of this class is to enable dependency injection on domain layer repository contract.
 */
@Component
public class DriverRepositoryImpl implements DriverRepository
{

    private DriverCrudRepository driverCrudRepository;


    @Override
    public List<Driver> findByOnlineStatus(OnlineStatus onlineStatus)
    {
        return driverCrudRepository.findByOnlineStatus(onlineStatus);
    }


    @Override
    public Optional<Driver> findById(Long driverId)
    {
        return driverCrudRepository.findById(driverId);
    }


    @Override
    public Driver save(Driver driver)
    {
        return driverCrudRepository.save(driver);
    }


    @Override
    public Iterable<Driver> findAllByOnlineStatusOrUsername(OnlineStatus onlineStatus, String username)
    {

        return driverCrudRepository.findByOnlineStatusOrUsername(onlineStatus, username);
    }


    @Autowired
    public void setDriverCrudRepository(DriverCrudRepository driverCrudRepository)
    {
        this.driverCrudRepository = driverCrudRepository;
    }
}
