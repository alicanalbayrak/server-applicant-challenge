package com.mytaxi.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.application.dto.DriverDTO;
import com.mytaxi.domain.GeoCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DriverControllerTest
{

    private MockMvc mockMvc;

    @Autowired
    private DriverController driverController;


    static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    @BeforeEach
    void setUp()
    {
        mockMvc = standaloneSetup(this.driverController).build();
    }


    @Test
    void getDriver() throws Exception
    {

        long driverId = 1L;

        mockMvc.perform(get("/v1/drivers/{driverId}", driverId)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.username", is("driver01")))
            .andExpect(jsonPath("$.password", is("driver01pw")))
            .andReturn();

    }


    @Test
    void createDriver() throws Exception
    {

        DriverDTO driverDTO = DriverDTO.newBuilder()
            .setId(90L)
            .setUsername("john")
            .setPassword("pa$$word")
            .setCoordinate(new GeoCoordinate(20, 30))
            .createDriverDTO();

        mockMvc.perform(post("/v1/drivers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(driverDTO)))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.username", is("john")))
            .andExpect(jsonPath("$.password", is("pa$$word")))
            .andReturn();

    }


    @Test
    void deleteDriver() throws Exception
    {

        Long driverId = 1L;

        mockMvc.perform(delete("/v1/drivers/{driverId}", driverId)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

    }


    @Test
    void getDriverWithId_and_updateLocation() throws Exception
    {

        Long driverId = 2L;
        mockMvc.perform(put("/v1/drivers/{driverId}", driverId)
            .contentType(MediaType.APPLICATION_JSON)
            .param("longitude", "40")
            .param("latitude", "30"))
            .andDo(print())
            .andExpect(status().isOk());

        mockMvc.perform(get("/v1/drivers/{driverId}", driverId)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(2)))
            .andExpect(jsonPath("$.username", is("driver02")))
            .andExpect(jsonPath("$.password", is("driver02pw")))
            .andExpect(jsonPath("$.coordinate.latitude", is(30.0)))
            .andExpect(jsonPath("$.coordinate.longitude", is(40.0)))
            .andReturn();

    }


    @Test
    void findDrivers()
    {

    }

}