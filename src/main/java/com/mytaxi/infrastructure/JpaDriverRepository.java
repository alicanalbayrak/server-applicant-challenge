package com.mytaxi.infrastructure;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.DriverRepository;
import com.mytaxi.domain.OnlineStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A repository for Manufacturer entities implemented with Spring Data Jpa
 */
@Component
public class JpaDriverRepository implements DriverRepository
{

    private final CrudDriverRepository crudDriverRepository;


    @Autowired
    public JpaDriverRepository(CrudDriverRepository crudDriverRepository)
    {
        this.crudDriverRepository = crudDriverRepository;
    }


    @Override
    public List<Driver> findByOnlineStatus(OnlineStatus onlineStatus)
    {
        return crudDriverRepository.findByOnlineStatus(onlineStatus);
    }


    @Override
    public Optional<Driver> findById(Long driverId)
    {
        return crudDriverRepository.findById(driverId);
    }


    @Override
    public Driver save(Driver driver)
    {
        return crudDriverRepository.save(driver);
    }
}
