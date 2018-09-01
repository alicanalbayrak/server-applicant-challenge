package com.mytaxi.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DriverServiceTest
{

    @Mock
    private DriverRepository driverRepository;


    @InjectMocks
    private DriverService driverService;


    @BeforeEach
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Trying to create driver with wrong input")
    public void findById()
    {

        Driver driver = new Driver("john", "doe");
        when(driverRepository.save(driver)).thenThrow(new DuplicateKeyException("Duplicate key detected..."));

        Executable driverCreateExecutable = () -> driverService.create(driver);

        assertThrows(ConstraintsViolationException.class, driverCreateExecutable, "some message");

    }

}