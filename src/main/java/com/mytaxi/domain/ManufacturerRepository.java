package com.mytaxi.domain;

import java.util.Optional;

public interface ManufacturerRepository
{
    Optional<Manufacturer> findByName(String name);
}