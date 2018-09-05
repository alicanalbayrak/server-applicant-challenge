package com.mytaxi.application.controller;

import com.mytaxi.application.dto.CarDTO;
import com.mytaxi.application.service.CarService;
import com.mytaxi.domain.EngineType;
import com.mytaxi.domain.Manufacturer;
import com.mytaxi.domain.Rating;
import com.mytaxi.domain.SeatCount;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CarControllerTest
{

    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @Autowired
    private CarController carController;


    @BeforeAll
    public static void setupBeforeAll()
    {
        MockitoAnnotations.initMocks(CarController.class);
    }


    private static List<CarDTO> getCarList()
    {

        CarDTO carDTO1 = CarDTO.builder()
            .id(1L)
            .convertible(false)
            .engineType(EngineType.DISEL)
            .licensePlate("0123ABC")
            .manufacturer(Manufacturer.createManufacturer("Toyota").getName())
            .rating(Rating.of(3.4).getValue())
            .seatCount(SeatCount.of(4).getValue())
            .build();

        CarDTO carDTO2 = CarDTO.builder()
            .id(2L)
            .convertible(true)
            .engineType(EngineType.ELECTRIC)
            .licensePlate("456DEF")
            .manufacturer(Manufacturer.createManufacturer("Audi").getName())
            .rating(Rating.of(1.5).getValue())
            .seatCount(SeatCount.of(2).getValue())
            .build();

        CarDTO carDTO3 = CarDTO.builder()
            .id(3L)
            .convertible(false)
            .engineType(EngineType.GAS)
            .licensePlate("789GHI")
            .manufacturer(Manufacturer.createManufacturer("Mercedes").getName())
            .rating(Rating.of(4.5).getValue())
            .seatCount(SeatCount.of(4).getValue())
            .build();

        return Arrays.asList(carDTO1, carDTO2, carDTO3);

    }


    @BeforeEach
    void setUpBeforeEachTest()
    {
        mockMvc = standaloneSetup(carController).build();
    }


    @Test
    void listCars() throws Exception
    {

        when(carService.listAllCars()).thenReturn(getCarList());
        mockMvc.perform(get("/v1/car/list").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    }


    @Test
    void getCar() throws Exception
    {
        long carId = 1L;
        mockMvc.perform(get("/v1/car/{carId}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    }


    @Test
    void createCar() throws Exception
    {

        mockMvc.perform(get("/v1/car/list")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    }


    @Test
    void updateCar() throws Exception
    {
        mockMvc.perform(get("/v1/car/list")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    }


    @Test
    void deleteCar() throws Exception
    {
        mockMvc.perform(get("/v1/car/list")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    }

}