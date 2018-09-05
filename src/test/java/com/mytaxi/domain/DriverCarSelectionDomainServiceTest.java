package com.mytaxi.domain;

import com.mytaxi.domain.shared.CarAlreadyInUseException;
import com.mytaxi.domain.shared.DriverAlreadySelectedCarException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import com.mytaxi.domain.shared.OfflineDriverCarSelectionException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DriverCarSelectionDomainServiceTest
{

    @Mock
    private CarDomainService carDomainService;

    @Mock
    private DriverDomainService driverDomainService;

    @Mock
    private DriverCarSelectionRepository driverCarSelectionRepository;

    @InjectMocks
    private DriverCarSelectionDomainService driverCarSelectionDomainService;


    private static Driver getSampleDriver()
    {

        Driver driver = new Driver("john", "pa$$word");
        driver.setId(1L);
        driver.setOnlineStatus(OnlineStatus.ONLINE);

        return driver;
    }


    private static Car getSampleCar()
    {

        Car car = new Car();
        car.setId(1L);
        car.setLicensePlate(LicensePlate.of("test"));

        return car;

    }


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testSelectCarInUse_throwAlreadyInUse() throws EntityNotFoundException
    {

        Driver anotherDriver = getSampleDriver();
        Driver driver = getSampleDriver();
        driver.setId(2L);
        Car car = getSampleCar();

        DriverCarSelection driverCarSelection = new DriverCarSelection(anotherDriver.getId(), car.getId());

        when(driverDomainService.find(1L)).thenReturn(anotherDriver);
        when(driverDomainService.find(2L)).thenReturn(driver);
        when(carDomainService.find(1L)).thenReturn(car);
        when(driverCarSelectionRepository.findEntryByCarId(1L)).thenReturn(Optional.of(driverCarSelection));
        when(driverCarSelectionRepository.findEntryByDriverId(2L)).thenReturn(Optional.empty());

        assertThrows(
            CarAlreadyInUseException.class,
            () -> driverCarSelectionDomainService.selectCar(driver.getId(), car.getId()));

        verify(driverDomainService, times(1)).find(any(Long.class));
        verify(carDomainService, times(1)).find(any(Long.class));
        verify(driverCarSelectionRepository, times(1)).findEntryByDriverId(any(Long.class));
        verify(driverCarSelectionRepository, times(1)).findEntryByCarId(any(Long.class));

    }


    @Test
    void testDriverSelectedCar_tryToSelect_anotherOne() throws EntityNotFoundException
    {

        Driver driver = getSampleDriver();
        Car car = getSampleCar();

        DriverCarSelection driverCarSelection = new DriverCarSelection(driver.getId(), car.getId());

        when(driverDomainService.find(1L)).thenReturn(driver);
        when(carDomainService.find(1L)).thenReturn(car);
        when(driverCarSelectionRepository.findEntryByCarId(1L)).thenReturn(Optional.of(driverCarSelection));
        when(driverCarSelectionRepository.findEntryByDriverId(1L)).thenReturn(Optional.of(driverCarSelection));

        assertThrows(
            DriverAlreadySelectedCarException.class,
            () -> driverCarSelectionDomainService.selectCar(driver.getId(), car.getId()));

        verify(driverCarSelectionRepository, times(1)).findEntryByDriverId(any(Long.class));
        verify(driverCarSelectionRepository, times(0)).findEntryByCarId(any(Long.class));
        verify(driverDomainService, times(1)).find(any(Long.class));
        verify(carDomainService, times(0)).find(any(Long.class));
    }


    @Test
    void testSelectCarDriverOffline_throwOfflineDriverException() throws EntityNotFoundException
    {
        Driver driver = getSampleDriver();
        driver.setOnlineStatus(OnlineStatus.OFFLINE);
        Car car = getSampleCar();

        DriverCarSelection driverCarSelection = new DriverCarSelection(driver.getId(), car.getId());

        when(driverDomainService.find(1L)).thenReturn(driver);
        when(carDomainService.find(1L)).thenReturn(car);

        assertThrows(
            OfflineDriverCarSelectionException.class,
            () -> driverCarSelectionDomainService.selectCar(driver.getId(), car.getId()));

        verify(driverDomainService, times(1)).find(any(Long.class));
        verify(carDomainService, times(0)).find(any(Long.class));

    }


    @Test
    void deselectCar()
    {

    }
}