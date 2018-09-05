package com.mytaxi.infrastructure;

import com.mytaxi.domain.Manufacturer;
import com.mytaxi.domain.ManufacturerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This component is used to delegates repository calls to the Spring CrudRepository.
 * Main idea of this class is to enable dependency injection on domain layer repository contract.
 */
@Component
public class ManufacturerRepositoryImpl implements ManufacturerRepository
{

    private ManufacturerCrudRepository manufacturerCrudRepository;


    public ManufacturerRepositoryImpl(ManufacturerCrudRepository manufacturerCrudRepository)
    {
        this.manufacturerCrudRepository = manufacturerCrudRepository;
    }


    @Override
    public Optional<Manufacturer> findByName(String name)
    {
        return manufacturerCrudRepository.findByNameEqualsIgnoreCase(name);
    }


    @Autowired
    public void setManufacturerCrudRepository(ManufacturerCrudRepository manufacturerCrudRepository)
    {
        this.manufacturerCrudRepository = manufacturerCrudRepository;
    }
}
