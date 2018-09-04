package com.mytaxi.domain;

import com.mytaxi.domain.shared.ConstraintsViolationException;
import com.mytaxi.domain.shared.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarDomainServiceTest
{

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarDomainService carDomainService;


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testFindCarById() throws EntityNotFoundException
    {

        long carId = 1L;
        when(carRepository.findById(carId)).thenReturn(Optional.of(createNewCar()));

        Car car = carDomainService.find(1L);

        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotNull();
        assertThat(car.getConvertible()).isFalse();
        assertThat(car.getEngineType()).isEqualTo(EngineType.DISEL);
        assertThat(car.getLicensePlate()).isEqualTo(LicensePlate.of("06XYZ999"));
        assertThat(car.getManufacturer().getName()).isEqualTo("Honda");
        assertThat(car.getRating()).isEqualTo(Rating.of(0d));
        assertThat(car.getSeatCount()).isEqualTo(SeatCount.of(4));
        assertThat(car.getDeleted()).isFalse();
    }


    @Test
    void testCreateNewCar() throws ConstraintsViolationException
    {

        Car car = createNewCar();
        when(carRepository.save(car)).thenReturn(car);

        carDomainService.create(createNewCar());

        verify(carRepository, times(1)).save(car);
    }


    @Test
    void delete() throws EntityNotFoundException
    {
        long carId = 1L;

        Car car = createNewCar();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        carDomainService.delete(carId);

        assertThat(car.getDeleted()).isTrue();

        verify(carRepository, times(1)).findById(carId);

    }


    @Test
    void update() throws ConstraintsViolationException, EntityNotFoundException
    {

        Car car = createNewCar();
        when(carRepository.findById(2L)).thenReturn(Optional.of(car));
        when(manufacturerRepository.findByName("Toyota")).thenReturn(Optional.of(Manufacturer.createManufacturer("Toyota")));

        carDomainService.update(2, createDifferentCar());

        // these attributes should not ve overwritten!
        assertThat(car.getId()).isEqualTo(1L);
        assertThat(car.getDeleted()).isFalse();

        assertThat(car.getConvertible()).isTrue();
        assertThat(car.getEngineType()).isEqualTo(EngineType.GAS);
        assertThat(car.getLicensePlate()).isEqualTo(LicensePlate.of("40AAB23"));
        assertThat(car.getManufacturer().getName()).isEqualTo(Manufacturer.createManufacturer("Toyota").getName());
        assertThat(car.getRating()).isEqualTo(Rating.of(0d));
        assertThat(car.getSeatCount()).isEqualTo(SeatCount.of(4));

    }


    private static Car createNewCar()
    {
        return Car.builder()
            .id(1L)
            .convertible(false)
            .engineType(EngineType.DISEL)
            .licensePlate(LicensePlate.of("06XYZ999"))
            .manufacturer(Manufacturer.createManufacturer("Honda"))
            .rating(Rating.of(0d))
            .seatCount(SeatCount.of(4))
            .dateCreated(ZonedDateTime.now())
            .deleted(false)
            .build();
    }


    private static Car createDifferentCar()
    {
        return Car.builder()
            .id(2L)
            .convertible(true)
            .engineType(EngineType.GAS)
            .licensePlate(LicensePlate.of("40AAB23"))
            .manufacturer(Manufacturer.createManufacturer("Toyota"))
            .rating(Rating.of(0d))
            .seatCount(SeatCount.of(4))
            .dateCreated(ZonedDateTime.now())
            .deleted(true)
            .build();
    }


}