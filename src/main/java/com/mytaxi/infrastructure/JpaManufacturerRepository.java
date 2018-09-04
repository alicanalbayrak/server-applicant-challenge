package com.mytaxi.infrastructure;

import com.mytaxi.domain.Manufacturer;
import com.mytaxi.domain.ManufacturerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A repository for Manufacturer entities implemented with Spring Data Jpa
 */
@Component
public class JpaManufacturerRepository implements ManufacturerRepository
{

    @Autowired
    private CrudManufacturerRepository crudManufacturerRepository;


    @Override
    public Optional<Manufacturer> findByName(String name)
    {
        return crudManufacturerRepository.findByNameEqualsIgnoreCase(name);
    }
}
