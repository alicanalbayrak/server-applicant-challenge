package com.mytaxi.domain;

import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Unit Tests of DriverService")
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
    @DisplayName("Trying to create same driver again throws an exception")
    public void createDuplicateDriver()
    {

        Driver driver = createNewDriver();
        when(driverRepository.save(driver)).thenThrow(new DuplicateKeyException("Duplicate key detected..."));

        Executable driverCreateExecutable = () -> driverService.create(driver);

        assertThrows(ConstraintsViolationException.class, driverCreateExecutable, "some message");
        verify(driverRepository, times(1)).save(driver);
    }


    @Test
    @DisplayName("Driver creation field check")
    public void createDriverAndCheckAttributes() throws ConstraintsViolationException
    {
        Driver driver = createNewDriver();
        when(driverRepository.save(driver)).thenReturn(driver);

        driverService.create(driver);

        assertNull(driver.getId());
        assertEquals(false, driver.getDeleted());
        assertEquals(OnlineStatus.OFFLINE, driver.getOnlineStatus());
        assertEquals("john", driver.getUsername());
        assertEquals("pa$$word", driver.getPassword());
        assertNull(driver.getCoordinate());
        verify(driverRepository, times(1)).save(driver);
    }


    @Test
    @DisplayName("Update drivers location")
    public void updateDriverLocation() throws EntityNotFoundException
    {
        long driverId = 1L;
        double longitude = 88;
        double latitude = 0;

        Driver driver = createNewDriver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        driverService.updateLocation(driverId, longitude, latitude);

        assertEquals(new GeoCoordinate(latitude, longitude), driver.getCoordinate());
        verify(driverRepository, times(1)).findById(driverId);
    }


    @Test
    @DisplayName("Test delete attr set correctly")
    public void markDriverDeleted() throws EntityNotFoundException
    {

        long driverId = 1L;
        Driver driver = createNewDriver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));
        assertEquals(false, driver.getDeleted());

        driverService.delete(driverId);

        assertEquals(true, driver.getDeleted());
        verify(driverRepository, times(1)).findById(driverId);

    }

    private static Driver createNewDriver(){
        return Driver.create("john", "pa$$word");
    }

}