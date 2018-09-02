package com.mytaxi.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest
{

    private CarService carService = new CarService();


    @BeforeEach
    void setUp()
    {
    }


    @Test
    void testFindCarById() throws EntityNotFoundException
    {

        Car car = carService.find(1L);

        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotNull();
        assertThat(car.getConvertible()).isFalse();
        assertThat(car.getEngineType()).isEqualTo(EngineType.DISEL);
        assertThat(car.getLicensePlate()).isEqualTo(LicensePlate.of("06XYZ999"));
        assertThat(car.getManufacturer().getName()).isEqualTo("Honda");
        assertThat(car.getRating()).isEqualTo(Rating.of(0d));
        assertThat(car.getSeatCount()).isEqualTo(SeatCount.of(4));
    }


    @Test
    void testCreateNewCar() throws ConstraintsViolationException
    {
        Car car = carService.create(createNewCar());

        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotNull();
        assertThat(car.getConvertible()).isFalse();
        assertThat(car.getEngineType()).isEqualTo(EngineType.DISEL);
        assertThat(car.getLicensePlate()).isEqualTo(LicensePlate.of("06XYZ999"));
        assertThat(car.getManufacturer().getName()).isEqualTo("Honda");
        assertThat(car.getRating()).isEqualTo(Rating.of(0d));
        assertThat(car.getSeatCount()).isEqualTo(SeatCount.of(4));
    }



    @Test
    void delete()
    {
    }


    @Test
    void update()
    {
    }


    private static Car createNewCar()
    {
        return Car.builder()
            .convertible(false)
            .engineType(EngineType.DISEL)
            .licensePlate(LicensePlate.of("06XYZ999"))
            .manufacturer(Manufacturer.createManufacturer("Honda"))
            .rating(Rating.of(0d))
            .seatCount(SeatCount.of(4))
            .build();
    }


}