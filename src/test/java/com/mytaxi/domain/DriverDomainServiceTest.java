package com.mytaxi.domain;

import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Unit Tests of DriverServiceImpl")
class DriverDomainServiceTest
{

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverDomainService driverDomainService;


    private static Driver createNewDriver()
    {
        return new Driver("john", "pa$$word");
    }


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Driver creation field check")
    public void testCreateDriverAndCheckAttributes() throws ConstraintsViolationException
    {
        Driver driver = createNewDriver();
        when(driverRepository.save(driver)).thenReturn(driver);

        driverDomainService.create(driver);

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
    public void testUpdateDriverLocation() throws EntityNotFoundException
    {
        long driverId = 1L;
        double longitude = 88;
        double latitude = 0;

        Driver driver = createNewDriver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        driverDomainService.updateLocation(driverId, longitude, latitude);

        assertEquals(new GeoCoordinate(latitude, longitude), driver.getCoordinate());
        verify(driverRepository, times(1)).findById(driverId);
    }


    @Test
    @DisplayName("Test delete attr set correctly")
    public void testMarkDriverDeleted() throws EntityNotFoundException
    {

        long driverId = 1L;
        Driver driver = createNewDriver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));
        assertEquals(false, driver.getDeleted());

        driverDomainService.delete(driverId);

        assertEquals(true, driver.getDeleted());
        verify(driverRepository, times(1)).findById(driverId);

    }


    @Test
    @DisplayName("Trying to create same driver again throws an exception")
    public void createDuplicateDriver()
    {

        Driver driver = createNewDriver();
        when(driverRepository.save(driver)).thenThrow(new DuplicateKeyException("Duplicate key detected..."));

        Executable driverCreateExecutable = () -> driverDomainService.create(driver);

        assertThrows(ConstraintsViolationException.class, driverCreateExecutable, "some message");
        verify(driverRepository, times(1)).save(driver);
    }


    @Test
    void testFind() throws EntityNotFoundException
    {
        long driverId = 1L;

        when(driverRepository.findById(driverId)).thenReturn(Optional.of(createNewDriver()));

        Driver driver = driverDomainService.find(driverId);

        assertNotNull(driver);
        verify(driverRepository, times(1)).findById(driverId);
    }


    @Test
    void findDriversByOnlineStatus()
    {

        Driver drv1 = new Driver("drv1", "drv1pss");
        Driver drv2 = new Driver("drv2", "drv2pss");
        Driver drv3 = new Driver("drv3", "drv2pss");

        List<Driver> driverInputList = Arrays.asList(drv1, drv2, drv3);

        OnlineStatus onlineStatus = OnlineStatus.ONLINE;

        when(driverRepository.findByOnlineStatus(onlineStatus)).thenReturn(driverInputList);

        List<Driver> driverList = driverDomainService.find(onlineStatus);

        assertThat(driverList, not(IsEmptyCollection.empty()));

    }
}