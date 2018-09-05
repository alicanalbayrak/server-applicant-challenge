package com.mytaxi.infrastructure;

import com.mytaxi.domain.Manufacturer;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerCrudRepository  extends CrudRepository<Manufacturer, Long>
{
    Optional<Manufacturer> findByNameEqualsIgnoreCase(String name);
}
